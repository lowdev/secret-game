import React from 'react';
import axios from 'axios';
import { Redirect, Link } from "react-router-dom";
import { Button } from 'react-bootstrap';

class Room extends React.Component {
    constructor(props) {
        super(props);
        
        const { match: { params } } = this.props;

        this.state = {
            error: null,
            isLoading: true,
            roomId: params.id,
            name: '',
            users: [],
            redirect: null
        };
    }

    async componentDidMount() {
        axios.get('/api/rooms/' + this.state.roomId)
            .then(response => {
                const room = response.data;
                    console.log(room.id);
                    this.setState(
                        { 
                            isLoading: false,
                            roomId: room.id,
                            users: room.users
                        }
                    );
                  }
            ).catch(error => {
                this.setState(
                    {
                      isLoading: false,
                        error
                    });
                console.error('There was an error!', error);
            });

        const eventSource = new EventSource('/api/rooms/' + this.state.roomId + '/events/user'); 
        eventSource.onmessage = (event) => {
            const users = this.state.users;
            users.push(event.data);
            this.setState({users: users}); 
        };
        eventSource.onerror = (error) => {
            this.setState({error});
            console.error('error', error);
        }
    }
    
    render() {
        const { error, isLoading, redirect } = this.state;
        if (redirect) {
            return <Redirect to={redirect} />
        }
        if (error) {
            return (
            <div>
                <div>Error: {error.message}</div>
                <br></br>
                <this.BackButton />
            </div>);
        }
        if (isLoading) {
            return <div>Loading...</div>;
        }
        return (
            <div>
                <div>Welcome to {this.state.name} Room (id: {this.state.roomId})</div>
                {this.state.users.map(user => (
                    <li key={user}>
                        {user}
                    </li>
                ))}
                <div>Waiting for players...</div>
            </div>
        );
    }

    BackButton() {
        return (
          <Link to="/">
            <Button variant="secondary" size="lg">
              Go Back
            </Button>{' '}
          </Link>
        );
    }
}

export default Room;