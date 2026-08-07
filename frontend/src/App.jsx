import { useState } from "react";

function App() {

  const [message, setMessage] = useState("");

  async function callBackend() {
    const response = await fetch("http://localhost:8090/hello");
    const text = await response.text();
    setMessage(text);
  }

  return (
    <div style={{ padding: "20px" }}>
      <h1>SmartDesk Lite</h1>

      <button onClick={callBackend}>
        Connect Ticket Service
      </button>

      <h2>{message}</h2>
    </div>
  );
}

export default App;