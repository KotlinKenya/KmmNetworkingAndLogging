package app.kmmchat.android.screen.feed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.FeedItem
import app.kmmchat.FeedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    var navigateBack: MutableState<Boolean> = mutableStateOf(false)

    fun navigateToLastScreen(navigate: Boolean = true) {
        navigateBack.value = navigate
    }


    var navigationDestination: MutableState<String?> = mutableStateOf(null)

    fun navigateToDestination(route: String?) {
        navigationDestination.value = route
    }


    var newPostAlertVisible: MutableState<Boolean> = mutableStateOf(false)

    fun setNewPostAlertVisible(creating: Boolean = true) {
        newPostAlertVisible.value = creating
    }


    var feedItems: MutableState<List<FeedItem>> = mutableStateOf(emptyList())
    var refreshingFeedItems: MutableState<Boolean> = mutableStateOf(false)

    fun refreshFeedItems() = viewModelScope.launch {
        refreshingFeedItems.value = true
        delay(2500)
        feedItems.value = FeedRepository().getFeedItems()
        refreshingFeedItems.value = false
    }


    init {
        refreshFeedItems()
    }
}