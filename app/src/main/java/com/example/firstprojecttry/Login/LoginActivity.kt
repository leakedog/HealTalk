package com.example.firstprojecttry.Login


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firstprojecttry.Logic.DescriptionType
import com.example.firstprojecttry.Logic.Schedule
import com.example.firstprojecttry.Logic.UtilityClass.clientDescriptionNames
import com.example.firstprojecttry.Logic.UtilityClass.descriptionMap
import com.example.firstprojecttry.Logic.UtilityClass.descriptionNames
import com.example.firstprojecttry.Logic.UtilityClass.descriptionStates
import com.example.firstprojecttry.Logic.UtilityClass.executorDescriptionNames
import com.example.firstprojecttry.Login.Questions.DateQuestion
import com.example.firstprojecttry.Login.Questions.LocationQuestion
import com.example.firstprojecttry.Login.Questions.PhotoQuestion
import com.example.firstprojecttry.Login.Questions.ScheduleQuestion
import com.example.firstprojecttry.Login.Questions.SelectionQuestion
import com.example.firstprojecttry.Login.Questions.StringQuestion
import com.example.firstprojecttry.ProfileViewModel
import com.example.firstprojecttry.R
import com.example.firstprojecttry.ui.theme.Purple40


@Composable
fun PageIndicatorView(
    id: Int,
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedLength: Dp,
    animationDurationInMillis: Int,
    modifier: Modifier = Modifier,
    onClick : (Int) -> Unit = {},
) {

    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        )
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedLength
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        )
    )

    Surface(onClick = {
        onClick(id);
    }) {
        Canvas(
            modifier = modifier
                .size(
                    width = width,
                    height = defaultRadius,
                ),
        ) {
            drawRoundRect(
                color = color,
                topLeft = Offset.Zero,
                size = Size(
                    width = width.toPx(),
                    height = defaultRadius.toPx(),
                ),
                cornerRadius = CornerRadius(
                    x = defaultRadius.toPx(),
                    y = defaultRadius.toPx(),
                ),
            )
        }
    }

}


@Composable
fun PageIndicatorQuestions(
    modifier: Modifier = Modifier,
    selectedPage: Int = 0,
    selectedColor: Color = Color.Blue,
    defaultColor: Color = Color.LightGray,
    defaultRadius: Dp = 20.dp,
    selectedLength: Dp = 60.dp,
    space: Dp = 6.dp,
    animationDurationInMillis: Int = 300,
    onClick : (Int) -> Unit = {},
    onFinish : () -> Unit = {},
    list: MutableList<String>
) {
    var (isEnabledValue, changeEnabled) = remember { mutableStateOf(false)}
    val numberOfPages = list.size
    var stateGrid = rememberSaveable{mutableStateOf(HashMap<String, MutableState<Boolean>>())}
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space),
                modifier = modifier,

                ) {
                for (i in 0 until numberOfPages) {
                    val isSelected = (i == selectedPage)
                    PageIndicatorView(
                        id = i,
                        isSelected = isSelected,
                        selectedColor = selectedColor,
                        defaultColor = defaultColor,
                        defaultRadius = defaultRadius,
                        selectedLength = selectedLength,
                        animationDurationInMillis = animationDurationInMillis,
                    )

                }
            }
        }
        var size = 30.dp;
        if (descriptionMap[descriptionNames[selectedPage]]!!.type ==
            DescriptionType.SCHEDULE
        ) {
            size = 0.dp
        }
        Column(
            Modifier
                .padding(start = size, end = size, top = 60.dp)
        ) {
            println(descriptionMap[descriptionNames[selectedPage]]!!.type)
            println(descriptionNames[selectedPage])
            println(selectedPage)
            println(descriptionNames)
            val name = list[selectedPage]!!
            val field = descriptionMap[name]!!
            val typo = field.type
            if (typo == DescriptionType.DATE) {
                DateQuestion(onError = changeEnabled, name = name)
            }
            else{
                Text(
                        style = MaterialTheme.typography.headlineMedium,
                        text = field.title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp - size, end = 30.dp - size)
                    )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .padding(start = 30.dp - size, end = 30.dp - size),
                    text = field.body,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
                if (typo == DescriptionType.STRING || typo == DescriptionType.NUMBER){
                    StringQuestion(onError = changeEnabled, name = name)
                } else if (typo == DescriptionType.CHECKBOX) {
                    SelectionQuestion(name = name)
                } else if (typo == DescriptionType.SCHEDULE) {
                    ScheduleQuestion(stateGrid)
                } else if (typo == DescriptionType.PHOTO) {
                    PhotoQuestion(name = name)
                } else if (typo == DescriptionType.LOCATION) {
                    LocationQuestion(name = name)
                }
            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {
                        if (typo == DescriptionType.SCHEDULE) {
                            var toex : MutableMap<String, Boolean> = HashMap();
                            for (i in Schedule.keyNames) {
                                toex[i] = stateGrid.value[i]!!.value
                            }
                            descriptionStates["scheduleMap"] = toex
                            println(descriptionStates)
                        }
                        if (selectedPage != (numberOfPages - 1)) {
                            //TODO save data
                            onClick(selectedPage + 1);
                        } else {
                            onFinish()
                        }
                    },
                    modifier = Modifier
                        .padding(
                            bottom = 30.dp, end = 30.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (isEnabledValue) {
                                Purple40
                            } else {
                                Color.Gray
                            }
                        ),
                    enabled = isEnabledValue
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_bold_foreground),
                        contentDescription = "Next page UI questions",
                        modifier = Modifier.padding(10.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}




@Preview
@Composable
fun ExecutorQuestionPage(){
    val (selectedPage, setSelectedPage) = remember {
        mutableIntStateOf(0)
    }

        // NEVER use this, this is just for exampl

    PageIndicatorQuestions(
        selectedPage = selectedPage,
        defaultRadius = 10.dp,
        selectedLength = 40.dp,
        space = 2.dp,
        onClick = setSelectedPage,
        selectedColor = colorResource(id = R.color.purple_500),
        onFinish = {
            ProfileViewModel.firstUploadUser(1);
        },
        list = executorDescriptionNames
    )

}

@Preview
@Composable
fun ClientQuestionPage(){
    val (selectedPage, setSelectedPage) = remember {
        mutableIntStateOf(0)
    }

    // NEVER use this, this is just for exampl

    PageIndicatorQuestions(
        selectedPage = selectedPage,
        defaultRadius = 10.dp,
        selectedLength = 40.dp,
        space = 2.dp,
        onClick = setSelectedPage,
        selectedColor = colorResource(id = R.color.purple_500),
        onFinish = {
            ProfileViewModel.firstUploadUser(0);
        },
        list = clientDescriptionNames
    )

}




