package com.example.atlysassignment.ui.composeui

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    onActiveChangeCallback: (isActive: Boolean) -> Unit = {},
    onSearchQuery: (query: String) -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    SearchBar(
        query = searchQuery,
        onQueryChange = {
            searchQuery = it
            onSearchQuery(it)
        },
        onSearch = {
        },
        active = isActive,
        onActiveChange = {
            isActive = it
            if (it.not()) {
                searchQuery = ""
            }
            onActiveChangeCallback(it)
        },
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        placeholder = { Text("Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    ) {
        content()
    }
}