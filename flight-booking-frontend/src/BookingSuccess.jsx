import { useLocation, useNavigate } from "react-router-dom";

function BookingSuccess() {
  const location = useLocation();
  const navigate = useNavigate();

  const booking = location.state;

  return (
    <div className="container mt-5 text-center">
      <div className="card p-4 shadow">
        <h2 className="text-success">🎉 Booking Confirmed!</h2>

        <p><b>Booking ID:</b> {booking?.id}</p>
        <p><b>Passenger:</b> {booking?.passengers[0]?.name}</p>
        <p>
          <b>Flight:</b> {booking?.flight?.source} → {booking?.flight?.destination}
        </p>
        <p><b>Status:</b> {booking?.status}</p>

        <button
          className="btn btn-primary mt-3"
          onClick={() => navigate("/")}
        >
          Back to Home
        </button>
      </div>
    </div>
  );
}

export default BookingSuccess;