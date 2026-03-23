import { useEffect, useState } from "react";

function BookingHistory() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9090/booking")
      .then((res) => res.json())
      .then((data) => {
        console.log("API Response:", data); // 🔍 debug
        setBookings(data?.data || []);
      })
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div className="container mt-4">
      <h2 className="mb-4">📄 Booking History</h2>

      {bookings.length === 0 ? (
        <p>No bookings found</p>
      ) : (
        <div className="row">
          {bookings.map((b, index) => (
            <div className="col-md-4 mb-3" key={index}>
              <div className="card p-3 shadow">
                <h5>Booking ID: {b.id}</h5>

                <p>
                  <b>Passenger:</b> {b.passengers?.[0]?.name || "N/A"}
                </p>

                <p>
                  <b>Flight:</b> {b.flight?.source} → {b.flight?.destination}
                </p>

                <p>
                  <b>Status:</b> {b.status}
                </p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default BookingHistory;