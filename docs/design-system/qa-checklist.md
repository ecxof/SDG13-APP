# SDG-13 QA Checklist

## How to use
For each item: mark `[P]` pass, `[F]` fail, or `[N/A]`.

---

## Login Screen (`start.fxml`)

- [ ] App launches to login screen at 640×360
- [ ] SDG-13 logo visible (80×80) in top-left
- [ ] Username field: placeholder "Enter username" visible
- [ ] Password field: placeholder "Enter password" visible
- [ ] Password toggle shows/hides password text
- [ ] "Login" button has green filled style (`.btn-primary`)
- [ ] "Admin" button has green outlined style (`.btn-secondary`)
- [ ] Tab order: username → password → toggle → Login → Admin
- [ ] Empty credentials → error label "Please enter your username" appears in red
- [ ] Wrong credentials → error label "Incorrect password. Please try again."
- [ ] Valid credentials → navigates to main page (1280×720)
- [ ] Hero background image renders without stretching

## Main Dashboard (`mainpage.fxml`)

- [ ] Nav rail renders at 240px width with gray background
- [ ] ☰ toggle collapses nav to 64px
- [ ] ☰ toggle expands nav back to 240px
- [ ] Collapse/expand updates content pane width
- [ ] All 6 nav buttons show emoji + text when expanded
- [ ] All 6 nav buttons show only emoji when collapsed
- [ ] Active nav button: green left border + light green BG
- [ ] Header bar: 96px height, green BG, logo + "SDG-13" title
- [ ] Search button: gray filled, left-aligned
- [ ] Exit button: gray filled, positioned at bottom
- [ ] Clicking a nav button loads corresponding page in content pane

## Content Viewer (`page1.fxml` — representative)

- [ ] Full background image renders
- [ ] Dark overlay visible over hero/title area
- [ ] Hero title: large white bold text with drop shadow
- [ ] Subtitle: white text, readable against overlay
- [ ] Two cards render side by side with proper spacing
- [ ] Cards have white BG, 20px radius, elevation shadow
- [ ] Icon bubbles (colored circles) render correctly
- [ ] Card titles: 24px bold
- [ ] Card body text: 15px, wraps properly
- [ ] CTA buttons render as text links

## Admin Login (`adminlogin.fxml`)

- [ ] Admin password field with toggle works
- [ ] Login button validates password
- [ ] Exit button returns to main login
- [ ] Error label appears for wrong password

## Admin Editor (`adminpage.fxml` + `adminpage1.fxml`)

- [ ] Nav rail with 6 page buttons
- [ ] Clicking a page button loads editor in content area
- [ ] Page title renders (e.g. "Page 1 — Education")
- [ ] TextArea loads with full-width (990px), proper height
- [ ] TextArea uses `.text-area` styling (rounded border, focus glow)
- [ ] Save button positioned at bottom-right
- [ ] Save writes content to corresponding `.txt` file
- [ ] Nav toggle collapse/expand works same as main page

## Search (`search.fxml`)

- [ ] Title "Search" renders in 24px bold
- [ ] TextField with placeholder "Search topics..."
- [ ] Search button triggers search action
- [ ] ListView populates with results
- [ ] Selected list item: green highlight
- [ ] Window size: 500×400

## Feedback (`feedbackpage.fxml`)

- [ ] Centered card with shadow renders
- [ ] Close button (×) in top-right corner
- [ ] "Feedback" title in green
- [ ] Star rating (ControlsFX) functional
- [ ] Name field with label "Name"
- [ ] Suggestion field with label "Suggestion"
- [ ] Submit button saves feedback
- [ ] Error label appears if fields are empty
- [ ] After submit → navigates to thank-you page

## Share / Copy Link (`Copylink.fxml`)

- [ ] "Share link" title visible
- [ ] URL "https://sdgs.un.org/goals/goal13" displayed
- [ ] Copy icon/button visible
- [ ] Clicking copy → "Link copied to clipboard ✓" label appears
- [ ] Actual clipboard contains the URL

## Cross-Cutting

- [ ] All buttons have ≥ 44px min height
- [ ] All focused elements show blue 2px border ring
- [ ] Font renders as Segoe UI (or Arial fallback)
- [ ] All backgrounds: `#F3F4F6` (no white gaps)
- [ ] No horizontal scrollbars at 1280×800
- [ ] No horizontal scrollbars at 1024×768
- [ ] All text is readable (≥ 13px)
- [ ] No animation exceeds 300ms
