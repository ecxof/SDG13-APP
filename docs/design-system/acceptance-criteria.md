# SDG-13 Acceptance Criteria

## AC-1: Design Tokens
- [ ] `tokens.json` exists in `src/main/resources/com/example/sdg13ver5/`
- [ ] Every color value in `tokens.json` has a matching `-fx-*` variable in `styles.css`
- [ ] Primary color is `#166534`; background is `#F3F4F6`

## AC-2: Stylesheet
- [ ] `styles.css` uses `-fx-*` CSS variables instead of hardcoded hex in all component rules
- [ ] Font family is `"Segoe UI", "Arial", sans-serif` (with fallback)
- [ ] All button variants have `:hover`, `:pressed`, `:focused`, and `:disabled` states defined
- [ ] No inline styles remain on buttons that have a corresponding CSS class

## AC-3: Typography
- [ ] Type scale: hero (64px), h1 (44px), h2 (28px), h3 (20px), body-lg (16px), body (14px), caption (13px), small (12px)
- [ ] All text ≥ 13px minimum
- [ ] All body text uses `-fx-body-text` color token

## AC-4: Spacing & Layout
- [ ] Nav rail: 240px expanded, 64px collapsed
- [ ] Header: 96px fixed height
- [ ] All buttons: ≥ 44px min height (touch target)
- [ ] App renders without overflow at 1280×800 and 1024×768

## AC-5: Component Behavior
- [ ] Password toggle switches between hidden/visible states without losing input
- [ ] Nav collapse/expand updates all button widths and the content pane
- [ ] Active nav button shows green left border indicator
- [ ] Admin save writes TextArea content to correct `.txt` file

## AC-6: Accessibility
- [ ] All text color pairs meet WCAG AA contrast (≥ 4.5:1 normal, ≥ 3:1 large)
- [ ] All interactive elements are keyboard focusable via Tab
- [ ] Focus ring visible on all focused interactive elements (blue 2px border)
- [ ] Focus order matches visual top-to-bottom, left-to-right reading order

## AC-7: Animations
- [ ] No animation duration exceeds 300ms
- [ ] Nav collapse/expand is smooth (not instant)
- [ ] Button hover transitions are smooth

## AC-8: Error States
- [ ] Login: error label appears below password field for invalid credentials
- [ ] Feedback: error label appears if required fields empty on submit
- [ ] Admin: save error message appears if file write fails
- [ ] All error text uses `#B91C1C` color

## AC-9: Documentation
- [ ] `components.md` covers all 11 component types with specs
- [ ] `accessibility-report.md` has contrast ratios for all text pairs
- [ ] `qa-checklist.md` has per-screen test items
- [ ] `test-cases.md` has executable test case descriptions
