package com.example.firstprojecttry

import com.example.firstprojecttry.Logic

fun getExecutor(): Logic.Executor{
    return Logic.Executor(1,
        "Vasya",
        Logic.Description(),
        Logic.Schedule(),
        25,
        Logic.Photo("https://firebasestorage.googleapis.com/v0/b/healtalk-7ab6c.appspot.com/o/images%2F4bbea1c0-8d9f-45d7-bc07-c1992e674b40?alt=media&token=99cde52c-5f50-4f50-a8c3-109ec9538645"),
            Logic.Side.EXECUTORHOME,
        Logic.Location(12.0, 13.0),
            "97518.325"
        )

}
