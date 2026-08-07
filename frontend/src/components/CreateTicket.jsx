import { useState } from "react";
import api from "../api";

function CreateTicket({refresh}){

    const[ticket, setTicket] = useState(
        {
            title:"",
            description:"",
            status:"OPEN",
            priority:"HIGH"
        }
    );
    function handleChange(e){
        setTicket({...ticket,[e.target.name]:e.target.value });
    }
     async function saveTicket(e){
        e.preventDefault();
        await api.post("/tickets",ticket);

        setTicket({
            title:"",
            description:"",
            status:"OPEN",
            priority:"HIGH"
        });
        refresh();
     }


    return(
        <form onSubmit={saveTicket}>
           <h2>Create Ticket</h2>
            <input
                name="title"
                placeholder="Title"
                value={ticket.title}
                onChange={handleChange}
            />
            <br/><br/>
            <textarea
                name="description"
                placeholder="Description"
                value={ticket.description}
                onChange={handleChange}
            />
            <br/><br/>

            <button>Create Ticket</button>
        </form>
    );
}

export default CreateTicket;