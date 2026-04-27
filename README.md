## Design Decisions

### Why were these design decisions made?

The project was designed with a clear separation of responsibilities:

- The `Hand` class is responsible only for managing cards and jokers (adding, removing, and playing them).
- The evaluation logic is separated into different classes (`PokerHand`, `HandPriority`), avoiding mixing data and logic.
- Each poker hand (e.g., `Pair`, `Straight`, `Flush`) implements its own detection method, allowing independent and modular validation.

This design makes the system easier to extend and maintain.

---

### How is the code organized?

The code is organized into logical components:

- **Model layer**:
    - `Card`, `Rank`, `Suit`, `Joker`
- **Game state**:
    - `Hand`
- **Evaluation logic**:
    - `PokerHand` and its subclasses
    - `HandPriority`
- **Scoring**:
    - `Score`

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

- **Encapsulation**:  
  Internal collections (`_cards`, `_jokers`) are protected and exposed through read-only methods.

---

### Additional considerations

- Index-based removal uses descending order to avoid shifting issues in mutable collections.
- `playHand` first collects selected cards and then removes them to ensure correctness.
- Mutable collections (`ArrayBuffer`) were used to simplify operations required by the assignment.