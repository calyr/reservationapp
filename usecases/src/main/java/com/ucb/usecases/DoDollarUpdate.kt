package com.ucb.usecases
import com.ucb.data.RealDatabaseRepository
import kotlinx.coroutines.flow.Flow

class DoDollarUpdate(
    val repository: RealDatabaseRepository
) {
    fun invoke(): Flow<String> {
        return repository.getDollarUpdates()
    }
}