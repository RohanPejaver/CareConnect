package com.example.careconnect.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.careconnect.R
import com.example.careconnect.components.DoctorSearchScreen
import com.example.careconnect.components.HomeCard
import com.example.careconnect.components.HospitalMap
import com.example.careconnect.components.Logo
import com.example.careconnect.components.getReadableLocation
import com.example.careconnect.components.getUserLocation
import com.example.careconnect.navigation.Screen
import com.example.careconnect.screens.about.AboutScreen
import com.example.careconnect.screens.chat.ChatScreen
import com.example.careconnect.screens.chat.ChatViewModel
import com.example.careconnect.screens.diagnostic.DiagnosticOne
import com.example.careconnect.screens.diagnostic.DiagnosticThree
import com.example.careconnect.screens.diagnostic.DiagnosticTwo
import com.example.careconnect.screens.diagnostic.DiagnosticViewModel
import com.example.careconnect.screens.diagnostic.ResultScreen
import com.example.careconnect.screens.help.HelpScreen
import com.example.careconnect.screens.legal.PrivacyPolicyScreen
import com.example.careconnect.screens.legal.TermsAndConditionsScreen
import com.example.careconnect.screens.library.LibraryScreen
import com.example.careconnect.screens.profile.ProfileScreen
import com.example.careconnect.screens.search.SearchScreen
import com.example.careconnect.screens.search.SearchViewModel
import com.example.careconnect.screens.settings.SettingsScreen
import com.example.careconnect.screens.support.SupportScreen
import com.example.careconnect.screens.vaccination.VaccinationScreen
import com.example.careconnect.screens.vaccination.VaccinationViewModel
import com.example.careconnect.ui.theme.my_primary
import com.example.careconnect.ui.theme.my_secondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PatientHomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val user by viewModel.user.collectAsStateWithLifecycle()

    data class MenuItem(
        val id: String,
        val title: String,
        val contentDescription: String,
        val screen: Screen,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector
    )

    data class BottomBarItem(
        val id: String,
        val title: String,
        val contentDescription: String,
        val screen: Screen,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()

    val menuItems = listOf(
        MenuItem(
            id = "settings",
            title = "Settings",
            contentDescription = "Go to Settings",
            screen = Screen.Settings,
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
        MenuItem(
            id = "account",
            title = "Account",
            contentDescription = "Go to Account",
            screen = Screen.Profile,
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
        ),
        MenuItem(
            id = "help",
            title = "Help",
            contentDescription = "Get More Information",
            screen = Screen.Help,
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
        )
    )

    val bottomBarItems = listOf(
        BottomBarItem(
            id = "support",
            title = "CareBot",
            contentDescription = "Go to CareBot",
            screen = Screen.Support,
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
        ),
        BottomBarItem(
            id = "chat",
            title = "Chat",
            contentDescription = "Go to Chat",
            screen = Screen.Search,
            selectedIcon = Icons.AutoMirrored.Filled.Chat,
            unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
        ),
        BottomBarItem(
            id = "home",
            title = "Home",
            contentDescription = "Go to Home",
            screen = Screen.PatientHome,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomBarItem(
            id = "library",
            title = "Wellness",
            contentDescription = "Go to Disease Wellness",
            screen = Screen.Library,
            selectedIcon = Icons.Filled.AddChart,
            unselectedIcon = Icons.Outlined.AddChart,
        ),
        BottomBarItem(
            id = "settings",
            title = "Settings",
            contentDescription = "Go to Settings",
            screen = Screen.Settings,
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )

    var selectedDrawerIndex by rememberSaveable { mutableIntStateOf(-1) }
    var selectedBottomBarIndex by rememberSaveable { mutableIntStateOf(-1) }

    val navController = rememberNavController()

    val userLocation = getUserLocation(context)
    getReadableLocation(userLocation.latitude, userLocation.longitude, context)

    val chatViewModel: ChatViewModel = hiltViewModel()

    ModalNavigationDrawer(
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color.White) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(my_primary)
                        .height(80.dp)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Logo()
                }
                menuItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedDrawerIndex,
                        onClick = {
                            selectedDrawerIndex = index
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch { drawerState.close() }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedDrawerIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.contentDescription
                            )
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.White,
                            unselectedContainerColor = Color.White,
                        ),
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Logo() },
                    colors = TopAppBarColors(
                        containerColor = my_primary,
                        scrolledContainerColor = my_primary,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { navController.navigate(Screen.Profile.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.PersonOutline,
                                contentDescription = "Account",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    },
                    modifier = Modifier.clip(RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp)),
                )
            },
            bottomBar = {
                BottomNavigation(backgroundColor = my_primary, contentColor = Color.White) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    bottomBarItems.forEachIndexed { index, item ->
                        val isSelected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            },
                            label = { Text(text = item.title, color = Color.White) },
                            selected = isSelected,
                            onClick = {
                                selectedBottomBarIndex = index
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = Screen.PatientHome.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.PatientHome.route) {
                    LazyColumn(

                        state = lazyListState,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                    ) {
                        item {
                            Text(
                                text = "Welcome back ${user?.username}!",
                                color = my_secondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        item { Spacer(modifier = Modifier.height(12.dp)) }
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){
                                HomeCard(
                                    onClick = {
                                        scope.launch { lazyListState.animateScrollToItem(4) }
                                    },
                                    text = "Find Your Next Appointment",
                                    image = R.drawable.find_doctor
                                )
                                HomeCard(
                                    onClick = {
                                        navController.navigate(Screen.Library.route){
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    text = "Check Your Vaccinations",
                                    image = R.drawable.vaccination_check
                                )
                            }
                        }
                        item{ Spacer(modifier = Modifier.height(16.dp))}
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){
                                HomeCard(
                                    onClick = {
                                        navController.navigate(Screen.Library.route){
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    text = "Take a Diagnostic",
                                    image = R.drawable.diagnostic
                                )
                                HomeCard(
                                    onClick = {
                                        navController.navigate(Screen.Search.route){
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    text = "Talk to Your Doctors",
                                    image = R.drawable.doctor_chat
                                )
                            }
                        }
                        item { Spacer(modifier = Modifier.height(16.dp)) }
                        item { HospitalMap() }
                        item { Spacer(modifier = Modifier.height(8.dp)) }
                        item { DoctorSearchScreen() }
                    }
                }
                composable(Screen.Settings.route) { SettingsScreen(navController) }
                composable(Screen.Profile.route) { ProfileScreen() }
                composable(Screen.Help.route) { HelpScreen(navController) }
                composable(Screen.Support.route) { SupportScreen(navController) }
                composable(Screen.Library.route) { LibraryScreen(navController) }
                composable(Screen.Search.route) { SearchScreen(viewModel = SearchViewModel(), navController, modifier = Modifier) }
                composable(
                    route = "chat/{connectedUserId}",
                    arguments = listOf(
                        navArgument("connectedUserId") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    val connectedUserId = it.arguments?.getString("connectedUserId") ?: ""
                    ChatScreen(connectedUserId = connectedUserId, viewModel = chatViewModel, navController)
                }
                composable(Screen.DiagnosticOne.route) { DiagnosticOne(viewmodel = DiagnosticViewModel(), navController) }
                composable(Screen.DiagnosticTwo.route) { DiagnosticTwo(viewmodel = DiagnosticViewModel(), navController) }
                composable(Screen.DiagnosticThree.route) { DiagnosticThree(viewmodel = DiagnosticViewModel(), navController) }
                composable(Screen.Result.route) { ResultScreen(viewModel = DiagnosticViewModel(), navController = navController) }
                composable(Screen.Vaccination.route) { VaccinationScreen(viewModel = VaccinationViewModel(), navController) }
                composable(Screen.About.route) { AboutScreen(navController)}
                composable(Screen.Privacy.route) { PrivacyPolicyScreen(navController)}
                composable(Screen.TC.route) { TermsAndConditionsScreen(navController)}
            }
        }
    }
}
