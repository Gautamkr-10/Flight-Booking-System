import { useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import BookingSuccess from "./BookingSuccess";
import BookingHistory from "./BookingHistory";

function App() {
  const [from, setFrom] = useState("");
  const [to, setTo] = useState("");
  const [flights, setFlights] = useState([]);

const [selectedFlight, setSelectedFlight] = useState(null);
const [name, setName] = useState("");
const [age, setAge] = useState("");
const [gender, setGender] = useState("");
const [seatNumber, setSeatNumber] = useState("");
const [contactno, setContactno] = useState("");
const [email, setEmail] = useState("");

const navigate = useNavigate();

  const searchFlights = async () => {
  if (!from || !to) {
    alert("Please enter From and To");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:9090/flight/${from}/${to}`
    );

    const data = await response.json();
    setFlights(data.data);
  } catch (error) {
    console.error("Error fetching flights:", error);
  }
};

const handleBook = (flight) => {
  setSelectedFlight(flight);
};

const handleSubmitBooking = async () => {
  const bookingData = {
    status: "CONFIRMED",
    flight: {
      id: selectedFlight.id
    },
    passengers: [
      {
        name,
        age: parseInt(age),
        gender,
        seatNumber,
        contactno,
        email
      }
    ],
    payment: {
      amount: selectedFlight.price,
      mode: "DEBIT_CARD",
      status: "PENDING"
    }
  };

  try {
    const response = await fetch("http://localhost:9090/booking", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(bookingData)
    });

    const data = await response.json();
    navigate("/success", { state: data.data });

  } catch (error) {
    console.error("Booking error:", error);
  }
};

return (
  <Routes>
    <Route path="/"element={
    <div className="mt-4 px-3" style={{ minHeight: "100vh" }}>

      

      {/* Navbar */}
     <nav className="navbar navbar-dark bg-dark mb-4 px-4 rounded">
  <span className="navbar-brand fw-bold fs-4">
    ✈ Flight Booking
  </span>

  <button
    className="btn btn-outline-light"
    onClick={() => navigate("/history")}
  >
    📄 History
  </button>
</nav>

      {/* Center Wrapper */}
      <div className="d-flex flex-column align-items-center">

        {/* Heading */}
        <h1 className="text-center mb-4 fw-bold text-dark">
          ✈ Flight Booking System
        </h1>

        {/* Search Form */}
        <div
          className="card p-3 shadow mb-4"
          style={{ maxWidth: "800px", width: "100%" }}
        >
          <div className="row">
            <div className="col">
              <input
                type="text"
                className="form-control"
                placeholder="From"
                value={from}
                onChange={(e) => setFrom(e.target.value)}
              />
            </div>

            <div className="col">
              <input
                type="text"
                className="form-control"
                placeholder="To"
                value={to}
                onChange={(e) => setTo(e.target.value)}
              />
            </div>

            <div className="col">
              <button
                className="btn btn-primary w-100"
                onClick={searchFlights}
              >
                Search Flights
              </button>
            </div>
          </div>
        </div>

        {/* Flight List */}
        <div className="row justify-content-center w-100">
          {flights.map((flight, index) => (
            <div className="col-md-4 mb-3" key={index}>
              <div
                className="card shadow-lg border-0"
                style={{ borderRadius: "15px" }}
              >
                <div className="card-body text-center">
                  <h5 className="card-title fw-bold">
  ✈ {flight.airline}
</h5>

                  <p className="text-muted">
  📍 {flight.source} → {flight.destination}
</p>

                  <h6 className="text-primary">
  💰 ₹{flight.price}
</h6>

                  <button
                    className="btn btn-success mt-2"
                    onClick={() => handleBook(flight)}
                  >
                    Book Now
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* Booking Form */}
        {selectedFlight && (
          <div className="card mt-4 p-4 shadow-lg w-100" style={{ maxWidth: "800px" }}>
            <h4>Book Flight: {selectedFlight.airline}</h4>

            <div className="row">
              <div className="col-md-4">
                <input className="form-control mb-2" placeholder="Name" onChange={(e) => setName(e.target.value)} />
              </div>

              <div className="col-md-4">
                <input className="form-control mb-2" placeholder="Age" onChange={(e) => setAge(e.target.value)} />
              </div>

              <div className="col-md-4">
                <input className="form-control mb-2" placeholder="Gender" onChange={(e) => setGender(e.target.value)} />
              </div>

              <div className="col-md-4">
                <input className="form-control mb-2" placeholder="Seat Number" onChange={(e) => setSeatNumber(e.target.value)} />
              </div>

              <div className="col-md-4">
                <input className="form-control mb-2" placeholder="Contact Number" onChange={(e) => setContactno(e.target.value)} />
              </div>

              <div className="col-md-4">
                <input className="form-control mb-2" placeholder="Email" onChange={(e) => setEmail(e.target.value)} />
              </div>
            </div>

            <button
              className="btn btn-success mt-3"
              onClick={handleSubmitBooking}
            >
              Confirm Booking
            </button>
          </div>
        )}

      </div>
    </div>
  }
/>
    <Route path="/history" element={<BookingHistory />} />
    <Route path="/success" element={<BookingSuccess />} />
  </Routes>
);
}


export default App;