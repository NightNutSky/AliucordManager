package com.aliucord.manager.network.service

import com.aliucord.manager.network.dto.Version
import com.aliucord.manager.network.utils.ApiResponse
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AliucordGithubService(
    val github: GithubService,
    val http: HttpService
) {
    suspend fun getDataJson(): ApiResponse<Version> {
        return withContext(Dispatchers.IO) {
            http.request {
                url("https://raw.githubusercontent.com/$ORG/$MAIN_REPO/builds/data.json")
            }
        }
    }

    suspend fun getCommits(page: Int = 0) = github.getCommits(ORG, MAIN_REPO, page)
    suspend fun getLatestHermesRelease() = github.getLatestRelease(ORG, HERMES_REPO)
    suspend fun getLatestAliucordNativeRelease() = github.getLatestRelease(ORG, ALIUNATIVE_REPO)
    suspend fun getManagerReleases() = github.getReleases(ORG, MANAGER_REPO)
    suspend fun getContributors() = github.getContributors(ORG, MAIN_REPO)

    companion object {
        private const val ORG = "Aliucord"
        private const val MAIN_REPO = "Aliucord"
        private const val HERMES_REPO = "Hermes"
        private const val ALIUNATIVE_REPO = "AliucordNative"
        private const val MANAGER_REPO = "AliucordManager"

        const val KT_INJECTOR_URL = "https://raw.githubusercontent.com/$ORG/$MAIN_REPO/builds/Injector.dex"
        const val KOTLIN_DEX_URL = "https://raw.githubusercontent.com/$ORG/$MAIN_REPO/main/installer/android/app/src/main/assets/kotlin/classes.dex"
    }
}
