## Design Decisions

### Why were these design decisions made?

The project was designed with a clear separation of responsibilities:

- The `Hand` class is responsible only for managing cards and jokers (adding, removing, and playing them).
- The evaluation logic is separated into different classes (`PokerHand`, `HandPriority`), avoiding mixing data and logic.
- Each poker hand (e.g., `Pair`, `Straight`, `Flush`) implements its own detection method, allowing independent and modular validation.
- The scoring calculation delegates the responsibility to the specific components interacting in the game via the Double Dispatch pattern, preventing monolithic `if/else` structures.

This design makes the system easier to extend and maintain.
---

### How is the code organized?

The code is organized into logical components:

- **Model layer**:
    - `Card`, `Suit`, `Joker`.
    - `Rank` hierarchy, categorized through traits like `Even`, `Odd`, and `FaceCard` to group specific card behaviors.
- **Game state**:
    - `Hand`.
- **Evaluation logic**:
    - `PokerHand` and its subclasses.
    - `HandPriority`.
- **Scoring**:
    - `Score`.

Each component has a clear and specific responsibility.

---

### Design patterns used

The project follows several design principles and patterns:

- **Separation of concerns**:  
  Data structures (`Card`, `Hand`) are separated from logic (`PokerHand`, `HandPriority`).

- **Strategy-like pattern**:  
  Each poker hand (e.g., `Pair`, `Straight`) encapsulates its own detection logic.

- **Template/abstraction reuse**:  
  `RankBasedHand` is used to avoid duplicating logic for hands based on rank counting.

- **Double Dispatch**:  
  Used extensively for score calculations. The `applyScore` method allows entities like `Card`, `Suit`, `Rank`, and `PokerHand` to pass themselves to a `Joker`. This enables each `Joker` to apply unique, context-specific effects depending on the exact object it interacts with, without checking instances manually.

- **Encapsulation**:  
  Internal collections (`_cards`, `_jokers`) are protected and exposed through read-only methods in the `Hand` class.

---

### Additional considerations

- Index-based removal uses descending order to avoid shifting issues in mutable collections.
- `playHand` first collects selected cards and then removes them to ensure correctness.
- Mutable collections (`ArrayBuffer`) were used to simplify operations required by the assignment.
- The entire codebase is fully documented using **Scaladoc** standards, describing the parameters, return values, and specific behaviors of classes, traits, and methods to guarantee high maintainability.
