package com.ucb.data.dollar

import kotlinx.coroutines.flow.Flow

interface IRealDatabaseDataSource {
    fun getDollarUpdates(): Flow<String>
}