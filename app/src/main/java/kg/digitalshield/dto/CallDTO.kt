package kg.digitalshield.dto

import java.util.Date

data class CallDTO(
    val phoneNumber: String,
    val date: Date,
    val callStatus: CallStatus = CallStatus.SAFE,
    val suspiciousPhrases: List<String> = emptyList(),
)