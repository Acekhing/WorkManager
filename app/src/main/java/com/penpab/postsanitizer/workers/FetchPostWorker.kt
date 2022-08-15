@file:Suppress("BlockingMethodInNonBlockingContext")

package com.penpab.postsanitizer.workers

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.penpab.postsanitizer.api.WordpressApi
import com.penpab.postsanitizer.dto.ApiResponse
import com.penpab.postsanitizer.dto.PostItem
import com.penpab.postsanitizer.utils.Constants.WORKER_DATA
import com.penpab.postsanitizer.utils.Constants.WORKER_ERROR_MSG
import com.penpab.postsanitizer.utils.NotificationUtils
import com.penpab.postsanitizer.utils.WorkerUtils.handleErrorCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*
import kotlin.random.Random

class FetchPostWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private fun createFile(): File{
        val filename = "posts"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

        storageDir?.mkdir()
        return File.createTempFile(filename,".json", storageDir)
    }

    private fun saveFileToJson(jsonString: String) {
        val output: Writer
        val file = createFile()
        output = BufferedWriter(FileWriter(file))
        output.write(jsonString)
        output.close()
    }

    private suspend fun startForegroundService(){
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationUtils.createNotification(context))
        )
    }

    override suspend fun doWork(): Result {
        // Show Notification
        startForegroundService()
        delay(2000)
        val response = WordpressApi.instance.fetchPosts(1, 10)

        response.body()?.let {
            return withContext(Dispatchers.IO) {
                try {
                    //val postString = Json.encodeToString(it)
                    //saveFileToJson(postString)

                    // Do something with response

                }catch (e: Exception){
                    return@withContext Result.failure(
                        workDataOf(
                            WORKER_ERROR_MSG to e.localizedMessage
                        )
                    )
                }
                return@withContext Result.success(
                    workDataOf(
                        WORKER_DATA to "DONE"
                    )
                )
            }
        }

        // Check if request was successful
        if (!response.isSuccessful){
            // Handle error
            return handleErrorCode(response.code())
        }

        return Result.failure(
            workDataOf(
                WORKER_ERROR_MSG to "Unknown error occurred"
            )
        )
    }
}