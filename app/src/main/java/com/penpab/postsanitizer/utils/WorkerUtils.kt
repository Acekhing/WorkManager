package com.penpab.postsanitizer.utils

import androidx.work.*
import okhttp3.ResponseBody
import retrofit2.Response

object WorkerUtils {
    fun OneTimeWorkRequest.Builder.provideConstraints(): OneTimeWorkRequest.Builder {
        return setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        )
    }

    fun handleErrorCode(code: Int): ListenableWorker.Result {
        // Handle error
        if (code.toString().startsWith("5")){
            // Internal error. Retry the worker
            return ListenableWorker.Result.retry()
        }
        if (code.toString().startsWith("4")){
            // Internal error. Retry the worker
            return ListenableWorker.Result.failure(
                workDataOf(
                    Constants.WORKER_ERROR_MSG to "Request Error [4**]"
                )
            )
        }
        if (code.toString().startsWith("3")){
            // Internal error. Retry the worker
            return ListenableWorker.Result.failure(
                workDataOf(
                    Constants.WORKER_ERROR_MSG to "Request Error. Further action needed 3**]"
                )
            )
        }
        return ListenableWorker.Result.failure(
            workDataOf(
                Constants.WORKER_ERROR_MSG to "Invalid request"
            )
        )
    }
}