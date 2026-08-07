function TicketList({tickets}){

    return (
        <div>
            <h2> Ticket List</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Priority</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        tickets.map(ticket =>(
                             <tr>
                        <td>{ticket.id}</td>
                        <td>{ticket.title}</td>
                        <td>{ticket.status}</td>
                        <td>{ticket.priority}</td>
                        </tr>
                        ) )
                       
                    }
                </tbody>
            </table>
        </div>
    );
}
export default TicketList;