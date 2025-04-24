package com.ucb.data

import com.ucb.data.dollar.IRealDatabaseDataSource
import kotlinx.coroutines.flow.Flow

class RealDatabaseRepository(
    val dataSource: IRealDatabaseDataSource
) {
    fun getDollarUpdates(): Flow<String> {
        return dataSource.getDollarUpdates()
    }
}