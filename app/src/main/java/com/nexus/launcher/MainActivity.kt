package com.nexus.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NexusHome()
        }
    }
}

@Composable
fun NexusHome() {
    var selectedTab by remember { mutableStateOf("首页") }
    val tabs = listOf("首页", "版本", "模组", "整合包", "资源包", "光影包", "设置")

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = NexusBlack
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                Text(
                    "NEXUS LAUNCHER",
                    color = NexusGreen,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "MINECRAFT JAVA EDITION",
                    color = Color.LightGray,
                    fontSize = 11.sp
                )

                Spacer(Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    tabs.take(3).forEach { tab ->
                        NexusTab(tab, selectedTab == tab) {
                            selectedTab = tab
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    tabs.drop(3).forEach { tab ->
                        NexusTab(tab, selectedTab == tab) {
                            selectedTab = tab
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    "WELCOME TO NEXUS",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "一起·共创·更好的 Minecraft 体验",
                    color = NexusGreen,
                    fontSize = 13.sp
                )

                Spacer(Modifier.height(24.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = NexusPanel
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(18.dp)) {
                        Text("当前游戏版本", color = Color.White)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Minecraft Java Edition",
                            color = NexusGreen,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "版本管理即将开放",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    "功能中心",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                listOf("模组", "整合包", "资源包", "光影包").forEach { item ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = NexusPanel
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            item,
                            color = NexusGreen,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NexusGreen,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "启动游戏",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    "NEXUS Launcher · Beta 0.6.0",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun NexusTab(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (selected) NexusGreen else Color.LightGray
        )
    ) {
        Text(
            title,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
