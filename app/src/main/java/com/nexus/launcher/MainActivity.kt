
package com.nexus.launcher

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NexusBlack = Color(0xFF080D0B)
private val NexusGreen = Color(0xFF39FF88)
private val NexusPanel = Color(0xFF14201A)
private val NexusMuted = Color(0xFF9AA69F)
private val NexusBlue = Color(0xFF668CFF)

private val nexusTabs = listOf(
    "首页",
    "版本",
    "模组",
    "整合包",
    "资源包",
    "光影包",
    "设置"
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            MaterialTheme {
                NexusHome()
            }
        }
    }
}

@Composable
fun NexusHome() {
    var selectedTab by remember {
        mutableStateOf("首页")
    }

    var statusMessage by remember {
        mutableStateOf("NEXUS Launcher 已就绪")
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(NexusBlack)
    ) {

  // 左侧导航栏
Column(
    modifier = Modifier
        .width(180.dp)
        .fillMaxHeight()
        .background(Color(0xFF0D1510))
        .padding(12.dp)
        .verticalScroll(rememberScrollState())
) {

            Text(
                text = "NEXUS",
                color = NexusGreen,
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "LAUNCHER",
                color = Color.White,
                fontSize = 11.sp,
                letterSpacing = 3.sp
            )

            Spacer(Modifier.height(28.dp))

            nexusTabs.forEach { tab ->

                val isSelected = selectedTab == tab

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(
                            color = if (isSelected) {
                                NexusPanel
                            } else {
                                Color.Transparent
                            },
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            selectedTab = tab
                            statusMessage = "已切换到$tab"
                        }
                        .padding(
                            horizontal = 12.dp,
                            vertical = 13.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(22.dp)
                            .background(
                                if (isSelected) {
                                    NexusGreen
                                } else {
                                    Color.Transparent
                                },
                                RoundedCornerShape(4.dp)
                            )
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = tab,
                        color = if (isSelected) {
                            NexusGreen
                        } else {
                            NexusMuted
                        },
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Text(
                text = "BETA 0.6.0",
                color = NexusGreen,
                fontSize = 11.sp
            )

            Text(
                text = "永远是为人民服务",
                color = NexusMuted,
                fontSize = 10.sp
            )
        }

        // 右侧主内容区
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(
                    horizontal = 22.dp,
                    vertical = 16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = "NEXUS LAUNCHER",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "MINECRAFT JAVA EDITION",
                        color = NexusMuted,
                        fontSize = 11.sp
                    )
                }

                Text(
                    text = "BETA 0.6.0",
                    color = NexusGreen,
                    fontSize = 12.sp
                )
            }

            Spacer(Modifier.height(24.dp))

            when (selectedTab) {

                "首页" -> {
                    NexusDashboard(
                        statusMessage = statusMessage,
                        onLaunchClick = {
                            statusMessage =
                                "游戏启动功能尚未接入"
                        },
                        onTabClick = { tab ->
                            selectedTab = tab
                            statusMessage = "已切换到$tab"
                        }
                    )
                }

                "版本" -> {
                    NexusFeaturePage(
                        title = "版本管理",
                        description = "管理 Minecraft Java Edition 游戏版本",
                        status = "版本下载与安装功能正在开发"
                    )
                }

                "模组" -> {
                    NexusFeaturePage(
                        title = "模组管理",
                        description = "管理和整理 Minecraft Mods",
                        status = "模组管理功能正在开发"
                    )
                }

                "整合包" -> {
                    NexusFeaturePage(
                        title = "整合包管理",
                        description = "管理 Minecraft 整合包",
                        status = "整合包功能正在开发"
                    )
                }

                "资源包" -> {
                    NexusFeaturePage(
                        title = "资源包管理",
                        description = "管理 Minecraft Resource Packs",
                        status = "资源包功能正在开发"
                    )
                }

                "光影包" -> {
                    NexusFeaturePage(
                        title = "光影包管理",
                        description = "管理 Minecraft Shader Packs",
                        status = "光影包功能正在开发"
                    )
                }

                "设置" -> {
                    NexusFeaturePage(
                        title = "启动器设置",
                        description = "配置 NEXUS Launcher",
                        status = "更多设置功能正在开发"
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = statusMessage,
                color = NexusMuted,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun NexusDashboard(
    statusMessage: String,
    onLaunchClick: () -> Unit,
    onTabClick: (String) -> Unit
) {

    Text(
        text = "WELCOME TO NEXUS",
        color = Color.White,
        fontSize = 27.sp,
        fontWeight = FontWeight.ExtraBold
    )

    Text(
        text = "一起 · 共创 · 更好的 Minecraft 体验",
        color = NexusGreen,
        fontSize = 13.sp
    )

    Spacer(Modifier.height(22.dp))

    // 游戏信息卡片
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = NexusPanel
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "当前游戏版本",
                    color = NexusMuted,
                    fontSize = 13.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Minecraft Java Edition",
                    color = NexusGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "尚未选择游戏版本",
                    color = NexusMuted,
                    fontSize = 12.sp
                )
            }

            Text(
                text = "NEXUS",
                color = NexusBlue,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

    Spacer(Modifier.height(20.dp))

    Text(
        text = "功能中心",
        color = Color.White,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(Modifier.height(12.dp))

    // 两列功能卡片
    val featureRows = listOf(
        listOf("版本", "模组"),
        listOf("整合包", "资源包"),
        listOf("光影包", "设置")
    )

    featureRows.forEach { rowItems ->

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            rowItems.forEach { item ->

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 10.dp)
                        .clickable {
                            onTabClick(item)
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = NexusPanel
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Text(
                            text = item,
                            color = NexusGreen,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "点击进入$item",
                            color = NexusMuted,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }

    Spacer(Modifier.height(10.dp))

    // 启动按钮
    Button(
        onClick = onLaunchClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = NexusGreen,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp)
    ) {

        Text(
            text = "启动游戏",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NexusFeaturePage(
    title: String,
    description: String,
    status: String
) {

    Spacer(Modifier.height(18.dp))

    Text(
        text = title,
        color = NexusGreen,
        fontSize = 27.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(Modifier.height(12.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = NexusPanel
        )
    ) {

        Column(
            modifier = Modifier.padding(22.dp)
        ) {

            Text(
                text = description,
                color = Color.White,
                fontSize = 17.sp
            )

            Spacer(Modifier.height(18.dp))

            Text(
                text = status,
                color = NexusMuted,
                fontSize = 13.sp
            )
        }
    }
}
