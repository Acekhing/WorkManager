package com.penpab.postsanitizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.WindowCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.penpab.postsanitizer.utils.Constants.UNIQUE_WORKER_NAME
import com.penpab.postsanitizer.utils.Constants.WORKER_DATA
import com.penpab.postsanitizer.utils.Constants.WORKER_ERROR_MSG
import com.penpab.postsanitizer.utils.WorkerUtils.provideConstraints
import com.penpab.postsanitizer.workers.FetchPostWorker


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val fetchPostRequest = OneTimeWorkRequestBuilder<FetchPostWorker>().apply {
            provideConstraints()
        }.build()
        val workManager = WorkManager.getInstance(applicationContext)
        setContentView(R.layout.activity_main)

        workManager.getWorkInfosForUniqueWorkLiveData(UNIQUE_WORKER_NAME)
            .observe(this){ workInfos ->
                val fetchPostRequestInfo = workInfos?.find { it.id == fetchPostRequest.id }
                val posts = fetchPostRequestInfo?.outputData?.getString(WORKER_DATA)
                val errorMsg = fetchPostRequestInfo?.outputData?.getString(WORKER_ERROR_MSG)
                Log.d("MainActivity", "Posts: $posts")
                Log.d("MainActivity", "Error: $errorMsg")
                findViewById<TextView>(R.id.textView).text = posts

                when(fetchPostRequestInfo?.state){
                    WorkInfo.State.ENQUEUED -> { showProgress() }
                    WorkInfo.State.RUNNING -> { showProgress() }
                    WorkInfo.State.SUCCEEDED -> { hideProgress() }
                    WorkInfo.State.FAILED -> { hideProgress() }
                    WorkInfo.State.BLOCKED -> { hideProgress() }
                    WorkInfo.State.CANCELLED -> { hideProgress() }
                    null -> Unit
                }
            }

        findViewById<Button>(R.id.button).setOnClickListener {
            workManager.beginUniqueWork(
                UNIQUE_WORKER_NAME,
                ExistingWorkPolicy.REPLACE,
                fetchPostRequest
            ).enqueue()
        }
    }

    private fun hideProgress() {
        findViewById<ProgressBar>(R.id.progress_circular).visibility = View.INVISIBLE
    }

    private fun showProgress() {
        findViewById<ProgressBar>(R.id.progress_circular).visibility = View.VISIBLE
    }
}