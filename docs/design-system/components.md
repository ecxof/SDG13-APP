# SDG-13 Component Specification

> Design system component reference for JavaFX 21. All CSS classes reference `styles.css` v2.0.
> Keyboard: every interactive element is focusable. Tab moves focus; Enter/Space activates.

---

## 1. Buttons

### 1.1 Primary Button `.btn-primary`

| Property | Value |
|---|---|
| Background | `#166534` (token: `-fx-primary`) |
| Text | White, 14px bold |
| Min height | 44px (WCAG touch target) |
| Padding | `10 24` |
| Border radius | 6px |

**States:**

| State | Change |
|---|---|
| `:hover` | BG → `#15803d` |
| `:pressed` | BG → `#14532d` |
| `:focused` | Blue 2px border (`#2563EB`) |
| `:disabled` | BG → `#9CA3AF`, text → `#E5E7EB`, cursor → default |

**Accessibility:** `accessibleRole="BUTTON"`, `accessibleText` must describe action (e.g. "Log in to your account").

**Animation:** Background color transition ≤ 200ms ease-out (applied in Java via `Timeline`).

**FXML Example:**
```xml
<Button text="Login" styleClass="btn-primary"
        accessibleText="Log in to your account"
        onAction="#login" />
```

---

### 1.2 Secondary Button `.btn-secondary`

| Property | Value |
|---|---|
| Background | Transparent |
| Text/border | `#166534` |
| Border | 2px solid |
| Min height | 44px |

**States:** Hover → light green BG `#F0FDF4` + darker green. Pressed → `#DCFCE7`. Focus → blue ring.

---

### 1.3 Danger Button `.btn-danger`

Gray filled button for destructive actions. BG `#6B7280`, hover → `#4B5563`.

---

### 1.4 Close Button `.btn-close`

Transparent, `×` glyph, 20px. Hover → gray BG. For modal dismiss.

---

### 1.5 Toggle Button `.btn-toggle`

Hamburger `≡`, 22px. Hover → gray BG with 6px radius. Used for nav collapse.

---

### 1.6 Password Toggle `.btn-password-toggle`

Eye icon toggle. Text swaps between `👁`/`⬤` programmatically. Focus → blue ring.

---

### 1.7 Search Nav Button `.btn-search`

Gray filled (`#6B7280`), left-aligned with emoji icon `🔍`. Full-width in nav rail.

---

### 1.8 Exit Nav Button `.btn-exit`

Gray filled, full-width in nav rail. Icon `❌`. Hover → darker gray.

---

### 1.9 Link Button `.btn-link` + `.btn-link-primary` / `.btn-link-blue`

Transparent BG, text-only CTA. Used for secondary inline actions.

---

## 2. Navigation Rail

### CSS: `.nav-rail`, `.nav-button`, `.nav-button-active`

| Property | Expanded | Collapsed |
|---|---|---|
| Width | 240px | 64px |
| Button text | `🔍  Search` | `🔍` |
| Transition | ≤ 300ms ease-out (Java `Timeline`) | Same |

**Active State:** `.nav-button-active` — BG `#F0FDF4`, text `#166534`, 4px green left border.

**Keyboard:** Tab through all nav items. Enter activates. Active button gets visual indicator.

**Accessibility:** Each nav button needs `accessibleText` with full label even when collapsed.

---

## 3. Form Fields

### CSS: `.text-field`, `.password-field`, `.text-area`

| Property | Value |
|---|---|
| Background | White |
| Border | 1px `#D1D5DB` |
| Focus border | 2px `#166534` + green glow |
| Error state | `.field-error` — 2px `#B91C1C` border |
| Placeholder | `#9CA3AF` |
| Font | 14px |

**Error Flow:**
1. Add `.field-error` styleClass to the field
2. Show error `Label` with `.error-text` styleClass below the field
3. Set `accessibleText` on the error label for screen readers

**Microcopy:**
- Empty username: `"Please enter your username"`
- Wrong password: `"Incorrect password. Please try again."`
- Empty feedback name: `"Please enter your name"`

---

## 4. Cards

### CSS: `.card`, `.card-elevated`

| Variant | Radius | Shadow |
|---|---|---|
| `.card` | 16px | `dropshadow 24px blur, 8px offset` |
| `.card-elevated` | 20px | `dropshadow 30px blur, 8px offset` |

**Content card structure:**
```xml
<VBox styleClass="card-elevated">
  <StackPane styleClass="icon-bubble, icon-bubble-green">
    <Label text="😊" styleClass="emoji-icon" />
  </StackPane>
  <Label text="Title" styleClass="card-title" />
  <Label text="Body..." styleClass="card-body" wrapText="true" />
</VBox>
```

---

## 5. Header Bar

### CSS: `.header-bar`, `.header-image-box`, `.header-title`

Fixed 96px height. Green BG with optional background image overlay at 25% opacity.

---

## 6. Search

### CSS: `.search-title`, `.list-view`

| Element | Spec |
|---|---|
| Title | 24px bold, fill `#1F2937` |
| TextField | `.text-field` with `promptText="Search topics..."` |
| Button | `.btn-primary` |
| ListView | `.list-view` with rounded borders, selected = green highlight |

**Live filtering:** Controller listens to `textProperty()` changes, filters `ObservableList`, updates ListView items. No debounce needed for small data sets.

**Keyboard:** Tab from search field to button to list view. Arrow keys navigate list items. Enter selects.

---

## 7. Feedback

### CSS: `.feedback-card`, `.feedback-title`

- Star rating via ControlsFX `.rating` component
- Name + Suggestion text fields with `.label-text` labels above
- Submit `.btn-primary`, Close `.btn-close`
- Error/success labels: `.error-text` / `.success-text`

---

## 8. Toast Notifications

### CSS: `.toast`, `.toast-success`, `.toast-error`

Positioned at top-center. Fade in 200ms, auto-dismiss after 3s, fade out 300ms.

```java
// Usage (in controller):
Label toast = new Label("Saved successfully ✓");
toast.getStyleClass().addAll("toast", "toast-success");
// Add to root pane, animate with FadeTransition
```

---

## 9. Admin Editor

### Components used:
- `.admin-page-title` — 40px bold Text
- `.text-area` — full-width editor
- `.btn-primary` — Save button
- `.admin-save-success` / `.admin-save-error` — status labels

**Save flow:**
1. User edits TextArea content
2. Clicks "Save" → writes to `Page_X_Data.txt`
3. Show `.admin-save-success` label: `"Changes saved successfully ✓"`
4. On error: show `.admin-save-error`: `"Failed to save. Please try again."`
5. Status label fades out after 3 seconds

---

## 10. Loading & Error States

### Loading: `.loading-overlay` + `.spinner`

Overlay covers content area while loading. Spinner is a rotating bordered circle (animate with `RotateTransition`, 1s infinite).

### Error State:

Show a centered card with error icon, message, and retry button:
```xml
<VBox alignment="CENTER" styleClass="card">
  <Label text="⚠️" styleClass="emoji-icon" />
  <Label text="Something went wrong" styleClass="h3" />
  <Label text="Unable to load content" styleClass="body-text" />
  <Button text="Try again" styleClass="btn-primary" />
</VBox>
```

---

## 11. Share / Copy Link

### CSS: `.share-title`, `.share-url`, `.share-success`

- Title: "Share link" in `.share-title`
- URL displayed as selectable Label
- Copy button with clipboard icon
- Success: `.share-success` — "Link copied to clipboard ✓"
