package com.example.spin_bottle.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Challenge(
    @DocumentId
    var id: String? = null,
    var description: String? = ""
)