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
fun getClient(): Logic.Client {
    return Logic.Client(
            "Vasya",
            Logic.Description(),
            1,
            Logic.Photo("https://firebasestorage.googleapis.com/v0/b/healtalk-7ab6c.appspot.com/o/images%2F2c5de8d8-0cf3-4b74-a9b1-d2126bb7f580?alt=media&token=1ed6aa11-ef0a-4cb8-b072-ddc87102ba66"),
            Logic.Side.EXECUTORHOME,
            "fuk",
            "35162.325"
        )

}