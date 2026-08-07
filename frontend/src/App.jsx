import { useEffect, useState } from "react";
import api from "./api";
import CreateTicket from "./components/CreateTicket";
import TicketList from "./components/TicketList";

function App() {

  const [tickets, setTickets] = useState([]);

   async function loadTickets() {

        const response = await api.get("/tickets");

        setTickets(response.data);

    }

    useEffect(() => {

        loadTickets();

    }, []);

  return (

        <div style={{padding:"20px"}}>

            <h1>SmartDesk Lite</h1>

            <CreateTicket refresh={loadTickets}/>

            <hr/>

            <TicketList tickets={tickets}/>

        </div>

    );
}

export default App;