# SDG-13 Test Cases

> TestFX-based test skeletons for JavaFX UI testing. These require adding TestFX to `pom.xml`:
> ```xml
> <dependency>
>     <groupId>org.testfx</groupId>
>     <artifactId>testfx-junit5</artifactId>
>     <version>4.0.18</version>
>     <scope>test</scope>
> </dependency>
> ```

---

## TC-01: Login Validation — Empty Fields

**What to test:** Submitting login with empty username and password shows an error.

**How:**
```java
@Test
void loginWithEmptyFields_showsError() {
    // Given: on login screen
    // When: click Login without entering anything
    clickOn("#login");  // assumes Login button has fx:id="login"
    
    // Then: error label should be visible with text
    Label errorLabel = lookup("#invalid_user_pass").query();
    assertThat(errorLabel.getText()).isNotEmpty();
    assertThat(errorLabel.getStyleClass()).contains("error-text");
}
```

---

## TC-02: Password Toggle

**What to test:** Toggle button switches between PasswordField (masked) and TextField (visible).

**How:**
```java
@Test
void togglePassword_switchesVisibility() {
    // Given: password field is visible, plain text field is hidden
    PasswordField passField = lookup("#password").query();
    TextField visField = lookup("#passwordVisible").query();
    assertThat(passField.isVisible()).isTrue();
    assertThat(visField.isVisible()).isFalse();
    
    // When: click toggle
    clickOn("#togglePasswordBtn");
    
    // Then: password hidden, plain text visible, content preserved
    assertThat(passField.isVisible()).isFalse();
    assertThat(visField.isVisible()).isTrue();
    assertThat(visField.getText()).isEqualTo(passField.getText());
}
```

---

## TC-03: Nav Rail Collapse

**What to test:** Clicking hamburger toggles nav width between 240px and 64px.

**How:**
```java
@Test
void navToggle_collapsesAndExpands() {
    // Given: on main page, nav rail at 240px
    Pane navRail = lookup("#navRail").query();
    assertThat(navRail.getPrefWidth()).isEqualTo(240.0);
    
    // When: click toggle
    clickOn("#navToggleBtn");
    
    // Then: nav rail collapsed to 64px
    assertThat(navRail.getPrefWidth()).isEqualTo(64.0);
    
    // When: click toggle again
    clickOn("#navToggleBtn");
    
    // Then: expanded back to 240px
    assertThat(navRail.getPrefWidth()).isEqualTo(240.0);
}
```

---

## TC-04: Nav Active State

**What to test:** Clicking a nav button adds `.nav-button-active` and removes from previous.

**How:**
```java
@Test
void navButton_showsActiveState() {
    // When: click first nav button
    clickOn("#button1");
    
    // Then: button1 has active style
    Button btn1 = lookup("#button1").query();
    assertThat(btn1.getStyleClass()).contains("nav-button-active");
    
    // When: click second nav button
    clickOn("#button2");
    
    // Then: button2 active, button1 not
    Button btn2 = lookup("#button2").query();
    assertThat(btn2.getStyleClass()).contains("nav-button-active");
    assertThat(btn1.getStyleClass()).doesNotContain("nav-button-active");
}
```

---

## TC-05: Content Page Load

**What to test:** Clicking a nav button loads the corresponding page in the content pane.

**How:**
```java
@Test
void navButton_loadsContentPage() {
    // Given: on main page
    Pane display = lookup("#display1").query();
    
    // When: click "Improve education"
    clickOn("#button1");
    
    // Then: display pane has children (loaded page)
    assertThat(display.getChildren()).isNotEmpty();
}
```

---

## TC-06: Admin Save Flow

**What to test:** Editing TextArea and clicking Save writes to file.

**How:**
```java
@Test
void adminSave_writesToFile() throws Exception {
    // Given: on admin page 1 editor
    TextArea editor = lookup("#Info1").query();
    
    // When: clear and type new content, then save
    editor.clear();
    clickOn(editor).write("Test content");
    clickOn(".btn-primary"); // Save button
    
    // Then: file contains new content
    String content = Files.readString(Path.of("Page_One_Data.txt"));
    assertThat(content).isEqualTo("Test content");
}
```

---

## TC-07: Search Filtering

**What to test:** Typing in search field filters ListView results.

**How:**
```java
@Test
void search_filtersResults() {
    // Given: on search page
    TextField searchField = lookup("#searchbar2").query();
    ListView listView = lookup("#listview").query();
    
    // When: type a search term
    clickOn(searchField).write("education");
    clickOn(".btn-primary"); // Search button
    
    // Then: list view contains matching items
    assertThat(listView.getItems()).isNotEmpty();
}
```

---

## TC-08: Feedback Validation

**What to test:** Submitting feedback with empty name shows error.

**How:**
```java
@Test
void feedback_emptyName_showsError() {
    // Given: on feedback page, name field empty
    // When: click Submit
    clickOn(".btn-primary"); // Submit button
    
    // Then: error label visible
    Label errorLabel = lookup("#feed_label").query();
    assertThat(errorLabel.getText()).isNotEmpty();
}
```

---

## TC-09: Clipboard Copy

**What to test:** Clicking copy button on share page copies URL to clipboard.

**How:**
```java
@Test
void sharePage_copiesToClipboard() {
    // Given: on copy link page
    // When: click invisible copy button
    // (Button overlays the copy icon image)
    
    // Then: clipboard contains the URL
    Clipboard clipboard = Clipboard.getSystemClipboard();
    assertThat(clipboard.getString()).isEqualTo("https://sdgs.un.org/goals/goal13");
    
    // And: success label visible
    Label success = lookup("#successcopied").query();
    assertThat(success.getText()).contains("copied");
}
```

---

## TC-10: CSS Token Consistency

**What to test:** Button styles use token-based colors, not hardcoded.

**How:** (Manual / code review)
1. Open `styles.css`
2. Search for all `.btn-primary` rules
3. Verify all color values use `-fx-primary`, `-fx-primary-hover`, etc.
4. No raw hex codes in button state rules

---

## TC-11: Focus Ring Visibility

**What to test:** All interactive elements show a visible focus ring when focused via keyboard.

**How:**
```java
@Test
void focusRing_visibleOnTabNavigation() {
    // Given: on login page
    // When: press Tab to move focus to Login button
    press(KeyCode.TAB).release(KeyCode.TAB); // repeat as needed
    
    // Then: Login button has focus and shows border
    Button loginBtn = lookup(".btn-primary").query();
    assertThat(loginBtn.isFocused()).isTrue();
    // Visual verification: border color should be #2563EB
}
```

---

## TC-12: Min Touch Target

**What to test:** All buttons meet 44px minimum height.

**How:**
```java
@Test
void allButtons_meetMinTouchTarget() {
    Set<Node> buttons = lookup(".button").queryAll();
    for (Node btn : buttons) {
        assertThat(((Region) btn).getMinHeight()).isGreaterThanOrEqualTo(44.0);
    }
}
```
