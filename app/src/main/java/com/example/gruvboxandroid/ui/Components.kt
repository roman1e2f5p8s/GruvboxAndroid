package com.example.gruvboxandroid.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gruvboxandroid.R
import com.example.gruvboxandroid.ui.theme.GruvboxTheme
import com.example.gruvboxandroid.ui.theme.gb_dark_outline
import com.example.gruvboxandroid.ui.theme.gb_light_outline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainContent(darkThemeOpt: Boolean?): Boolean {
    val b = darkThemeOpt ?: isSystemInDarkTheme()
    val darkTheme = remember { mutableStateOf(b) }

    GruvboxTheme(darkTheme = darkTheme.value) {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val showBottomSheet = remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = false,
        )

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheetExample()
            },
            drawerState = drawerState
        )
        {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBarExample(scope, drawerState, darkTheme, scrollBehavior)
                },
                bottomBar = {
                    BottomAppBarExample(showBottomSheet, sheetState, darkTheme)
                },
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                floatingActionButton = {
                    ExtendedFloatingActionButtonExample(scope, snackbarHostState)
                }
            ) { innerPadding ->
                ScreenContent(innerPadding)
            }
        }
    }

    return darkTheme.value
}

@Composable
fun ModalDrawerSheetExample() {
    ModalDrawerSheet(
        //modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.85).dp),
        drawerShape = RectangleShape
    ) {
        NavigationDrawerItem(
            label = { Text("Inbox") },
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Filled.MailOutline, null) },
            badge = { Badge { Text("25") } },
        )
        NavigationDrawerItem(
            label = { Text("Outbox") },
            selected = false,
            onClick = {},
            icon = { Icon(Icons.AutoMirrored.Outlined.Send, null) },
            badge = { Badge { Text("2") } },
        )
        NavigationDrawerItem(
            label = { Text("Favorites") },
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Filled.FavoriteBorder, null) },
            badge = { Badge { Text("10") } },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample(
    scope: CoroutineScope,
    drawerState: DrawerState,
    darkTheme: MutableState<Boolean>,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        title = { Text("Hello, Gruvbox!") },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    darkTheme.value = !darkTheme.value
                }
            ) {
                if (darkTheme.value) {
                    Icon(painterResource(R.drawable.baseline_dark_mode_24), null)
                } else {
                    Icon(painterResource(R.drawable.baseline_light_mode_24), null)
                }
            }
            DropDownMenuExample()
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun DropDownMenuExample() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // First section
            DropdownMenuItem(
                text = { Text("Profile") },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                onClick = { /* Do something... */ }
            )

            HorizontalDivider()

            // Second section
            DropdownMenuItem(
                text = { Text("Send Feedback") },
                leadingIcon = {
                    Icon(
                        painterResource(R.drawable.baseline_feedback_24),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.AutoMirrored.Outlined.Send,
                        contentDescription = null
                    )
                },
                onClick = { /* Do something... */ }
            )

            HorizontalDivider()

            // Third section
            DropdownMenuItem(
                text = { Text("About") },
                leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Help") },
                leadingIcon = {
                    Icon(
                        painterResource(R.drawable.baseline_help_outline_24),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        painterResource(R.drawable.baseline_open_in_new_24),
                        contentDescription = null
                    )
                },
                onClick = { /* Do something... */ }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun BottomAppBarExample(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    darkTheme: MutableState<Boolean>
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    AlertDialogExample(openAlertDialog, darkTheme)

    BottomAppBar(
        actions = {
            IconButton(
                onClick = {
                    openAlertDialog.value = true
                }
            ) {
                Icon(Icons.Filled.DateRange, null)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet.value = true
                }
            ) {
                Icon(Icons.Filled.Edit, "Show bottom sheet")
            }
            BottomSheetExample(showBottomSheet, sheetState)
        }
    )
}

@Composable
fun AlertDialogExample(openAlertDialog: MutableState<Boolean>, darkTheme: MutableState<Boolean>) {
    if (openAlertDialog.value) {
        AlertDialog(
            icon = {
                Icon(Icons.Filled.Warning, contentDescription = "Warning")
            },
            title = {
                Text(text = "Alert dialog")
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Alert dialog example with two buttons.")
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .border(
                                1.dp,
                                if (darkTheme.value) gb_dark_outline else gb_light_outline,
                                RoundedCornerShape(8.dp)
                            )
                    ) {
                        PrimaryContainerBox()
                    }
                }
            },
            onDismissRequest = {
                openAlertDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openAlertDialog.value = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openAlertDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@Composable
fun PrimaryContainerBox() {
    //HorizontalDivider()
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text("Primary and tertiary container:")
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            TimeInputExample()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInputExample() {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    TimeInput(state = timePickerState)
}

@ExperimentalMaterial3Api
@Composable
fun BottomSheetExample(showBottomSheet: MutableState<Boolean>, sheetState: SheetState) {
    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            shape = RectangleShape,
        ) {
            Text(
                text = "Swipe up to open sheet. Swipe down to dismiss.",
                modifier = Modifier.padding(16.dp)
            )
            IndeterminateCircularIndicatorExample()
        }
    }
}

@Composable
fun IndeterminateCircularIndicatorExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(32.dp),
        )
        Spacer(Modifier.width(8.dp))
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ExtendedFloatingActionButtonExample(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    ExtendedFloatingActionButton(
        text = { Text("Show snackbar") },
        icon = { Icon(Icons.Filled.Info, null) },
        onClick = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Snackbar",
                    actionLabel = "Action",
                    withDismissAction = true,
                )
            }
        }
    )
}

@Composable
fun ScreenContent(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            PrimaryBox()
        }
        item {
            SecondaryContainerBox()
        }
        item {
            OutlineBox()
        }
        item {
            ErrorBox()
        }
        item {
            ElevatedCardExample()
        }
    }
}

@Composable
fun PrimaryBox() {
    HorizontalDivider()
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text("Primary:")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {}
            ) {
                Text("Filled button")
            }
            ElevatedButton(
                onClick = {}
            ) {
                Text("Elevated button")
            }
            SwitchExample()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = {}
            ) {
                Text("Outlined button")
            }
            TextButton(
                onClick = {}
            ) {
                Text("Text button")
            }
            CheckboxExample()
            RadioButtonExample()
        }
        SliderExample()
    }
}

@Composable
fun SwitchExample() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            null
        })
}

@Composable
fun CheckboxExample() {
    var checked by remember { mutableStateOf(true) }

    Checkbox(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}

@Composable
fun RadioButtonExample() {
    var selected by remember { mutableStateOf(true) }

    RadioButton(
        selected = selected,
        onClick = {
            selected = !selected
        }
    )
}

@Composable
fun SliderExample() {
    var sliderPosition by remember { mutableFloatStateOf(2f) }

    Slider(
        value = sliderPosition,
        onValueChange = {
            sliderPosition = it
        },
        steps = 11,
        valueRange = 0f..10f,
    )
}

@Composable
fun SecondaryContainerBox() {
    HorizontalDivider()
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text("Secondary container:")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FilledTonalButton(
                onClick = {}
            ) {
                Text("Tonal button")
            }
            MultiChoiceSegmentedButton()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FilterChipExample()
            SingleChoiceSegmentedButtonExample()
        }
    }
    Spacer(Modifier.height(5.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceSegmentedButtonExample() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Day", "Month", "Week")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}

@Composable
fun FilterChipExample() {
    var selected by remember { mutableStateOf(true) }

    FilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = { Text("Filter chip") },
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    null,
                )
            }
        } else {
            null
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChoiceSegmentedButton() {
    val selectedOptions = remember {
        mutableStateListOf(false, false, false)
    }
    val options = listOf("Walk", "Ride", "Drive")

    MultiChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                checked = selectedOptions[index],
                onCheckedChange = {
                    selectedOptions[index] = !selectedOptions[index]
                },
                icon = { SegmentedButtonDefaults.Icon(selectedOptions[index]) },
                label = {
                    when (label) {
                        "Walk" -> Icon(
                            painterResource(R.drawable.baseline_directions_walk_24),
                            contentDescription = "Directions Walk"
                        )

                        "Ride" -> Icon(
                            painterResource(R.drawable.baseline_directions_bus_24),
                            contentDescription = "Directions Bus"
                        )

                        "Drive" -> Icon(
                            painterResource(R.drawable.baseline_directions_car_24),
                            contentDescription = "Directions Car"
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun OutlineBox() {
    HorizontalDivider()
    Column(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text("Outline:")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextFieldExample()
            Spacer(Modifier.width(2.dp))
            TextFieldExample()
        }
    }
    //Spacer(Modifier.height(5.dp))
}

@Composable
fun ErrorBox() {
    //HorizontalDivider()
    Column(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text("Error:")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextFieldExample(isError = true)
            Spacer(Modifier.width(2.dp))
            TextFieldExample(isError = true)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Badges:")
            Badge()
            Badge { Text("1") }
            Badge { Text("10") }
            Badge { Text("100") }
            Badge { Text("1000") }
            Badge { Text("10000") }
        }
    }
    //Spacer(Modifier.height(5.dp))
}

@Composable
fun OutlinedTextFieldExample(isError: Boolean = false) {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
        },
        modifier = Modifier
            //.fillMaxWidth(),
            .width(LocalConfiguration.current.screenWidthDp.dp / 2 - 5.dp),
        label = {
            if (isError) {
                Text("In error")
            } else {
                Text("Outlined")
            }
        },
        placeholder = { Text("Input") },
        trailingIcon = {
            Icon(Icons.Filled.Edit, null)
        },
        isError = isError,
        singleLine = true
    )
}

@Composable
fun TextFieldExample(isError: Boolean = false) {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        modifier = Modifier
            //.fillMaxWidth(),
            .width(LocalConfiguration.current.screenWidthDp.dp / 2 - 5.dp),
        label = {
            if (isError) {
                Text("In error")
            } else {
                Text("Text field")
            }
        },
        placeholder = { Text("Input") },
        trailingIcon = {
            Icon(Icons.Filled.Edit, null)
        },
        isError = isError,
        singleLine = true
    )
}

@Composable
fun ElevatedCardExample() {
    HorizontalDivider()
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Elevated card",
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center,
        )
    }
}