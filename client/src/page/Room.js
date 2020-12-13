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
            gameMasterName: '',
            userId: params.userid,
            userName: '',
            users: [],
            redirect: null
        };
    }

    getApiRoot() {
        const apiRoot = process.env.REACT_APP_API_ROOT
        if (apiRoot) {
            return apiRoot;
        }

        return '/';
    }

    async componentDidMount() {
        this.getUser();
        this.getRoom();

        const eventSource = new EventSource(this.getApiRoot() + 'api/rooms/' + this.state.roomId + '/events/user'); 
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

    getUser() {
        if (!this.state.userId) {
            return;
        }

        axios.get(this.getApiRoot() + 'api/rooms/' + this.state.roomId + '/users/' + this.state.userId)
            .then(response => {
                const user = response.data;
                    this.setState(
                        { 
                            isLoading: false,
                            userName: user.name
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
    }

    getRoom() {
        axios.get(this.getApiRoot() + 'api/rooms/' + this.state.roomId)
            .then(response => {
                const room = response.data;
                    this.setState(
                        { 
                            isLoading: false,
                            roomId: room.id,
                            gameMasterName: room.gameMasterName,
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

        console.log(this.state.userName)
        if (this.state.userName === this.state.gameMasterName && this.state.users.length === 4) {
            return (
                <div>
                    <div>Welcome {this.state.userName} to your Room (id: {this.state.roomId})</div>
                    {this.state.users.map(user => (
                        <li key={user}>
                            {user}
                        </li>
                    ))}
                    <div>Start party</div>
                </div>
            );
        }
        return (
            <div>
                <div>Welcome {this.state.userName} to {this.state.gameMasterName} Room (id: {this.state.roomId})</div>
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