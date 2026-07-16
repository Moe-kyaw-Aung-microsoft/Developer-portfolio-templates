package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          PortfolioScreen(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun PortfolioScreen(modifier: Modifier = Modifier) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 32.dp),
    verticalArrangement = Arrangement.spacedBy(32.dp)
  ) {
    item { HeroSection() }
    item { AboutSection() }
    item { SkillsSection() }
    item { ProjectsSection() }
    item { ContactSection() }
  }
}

@Composable
fun HeroSection() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Avatar placeholder
    Box(
      modifier = Modifier
        .size(120.dp)
        .clip(CircleShape)
        .background(
          Brush.linearGradient(
            colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
          )
        ),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Profile Picture",
        modifier = Modifier.size(64.dp),
        tint = MaterialTheme.colorScheme.onPrimary
      )
    }
    
    Spacer(modifier = Modifier.height(24.dp))
    
    Text(
      text = "Alex Developer",
      style = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = (-1).sp
      ),
      color = MaterialTheme.colorScheme.onBackground
    )
    
    Text(
      text = "Senior Android Engineer",
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.primary,
      modifier = Modifier.padding(top = 4.dp)
    )
  }
}

@Composable
fun AboutSection() {
  Column(modifier = Modifier.fillMaxWidth()) {
    SectionHeader(title = "About Me")
    Text(
      text = "Passionate Android engineer with a strong focus on clean architecture, Jetpack Compose, and building delightful user experiences. I love transforming complex problems into elegant, maintainable solutions.",
      style = MaterialTheme.typography.bodyLarge,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      lineHeight = 24.sp
    )
  }
}

@Composable
fun SkillsSection() {
  val skills = listOf(
    "Kotlin", "Jetpack Compose", "Coroutines", "Room", 
    "Retrofit", "Clean Architecture", "MVI/MVVM", "Firebase"
  )
  
  Column(modifier = Modifier.fillMaxWidth()) {
    SectionHeader(title = "Skills")
    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      skills.forEach { skill ->
        SkillChip(skill = skill)
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRow(
    horizontalArrangement: Arrangement.Horizontal,
    verticalArrangement: Arrangement.Vertical,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
        content = { content() }
    )
}

@Composable
fun SkillChip(skill: String) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
  ) {
    Text(
      text = skill,
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
      style = MaterialTheme.typography.labelLarge,
      fontWeight = FontWeight.Medium
    )
  }
}

data class Project(val name: String, val description: String, val tag: String)

@Composable
fun ProjectsSection() {
  val projects = listOf(
    Project("Weather Tracker", "A beautiful weather app utilizing open APIs and modern Compose UI.", "Compose"),
    Project("Task Master", "Offline-first task management with Room database and sync.", "Room"),
    Project("AI Notes", "Smart note-taking application powered by Gemini AI API.", "Gemini")
  )
  
  Column(modifier = Modifier.fillMaxWidth()) {
    SectionHeader(title = "Recent Projects")
    projects.forEach { project ->
      ProjectCard(project = project)
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

@Composable
fun ProjectCard(project: Project) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    shape = RoundedCornerShape(20.dp)
  ) {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Outlined.Code,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = project.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
        
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
          contentColor = MaterialTheme.colorScheme.primary
        ) {
          Text(
            text = project.tag,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
          )
        }
      }
      
      Spacer(modifier = Modifier.height(12.dp))
      
      Text(
        text = project.description,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}

@Composable
fun ContactSection() {
  Column(modifier = Modifier.fillMaxWidth()) {
    SectionHeader(title = "Let's Connect")
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      ContactButton(
        icon = Icons.Default.Email,
        label = "Email",
        modifier = Modifier.weight(1f)
      )
      ContactButton(
        icon = Icons.Outlined.Star,
        label = "GitHub",
        modifier = Modifier.weight(1f)
      )
    }
  }
}

@Composable
fun ContactButton(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
  Button(
    onClick = { },
    modifier = modifier.height(56.dp),
    shape = RoundedCornerShape(16.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.surfaceVariant,
      contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
  ) {
    Icon(imageVector = icon, contentDescription = label)
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = label, fontWeight = FontWeight.Bold)
  }
}

@Composable
fun SectionHeader(title: String) {
  Text(
    text = title,
    style = MaterialTheme.typography.titleLarge.copy(
      fontWeight = FontWeight.Bold
    ),
    color = MaterialTheme.colorScheme.onBackground,
    modifier = Modifier.padding(bottom = 16.dp)
  )
}

@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
  MyApplicationTheme {
    PortfolioScreen()
  }
}
