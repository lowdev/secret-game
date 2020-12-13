import React from 'react';
import { Button, Form } from 'react-bootstrap';
import axios from 'axios';
import { Redirect, Link } from 'react-router-dom';

class JoinRoom extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        error: null,
        isLoading: false,
        roomId: '',
        roomMasterName: '',
        name: '',
        redirect: null
      };
      this.okClick = this.okClick.bind(this);
      this.handleNameChange = this.handleNameChange.bind(this);
      this.handleRoomMasterNameChange = this.handleRoomMasterNameChange.bind(this);
    }

    componentDidMount() { }

    handleNameChange(event) {
      this.setState({name: event.target.value});
    }

    handleRoomMasterNameChange(event) {
      this.setState({roomMasterName: event.target.value});
    }

    getApiRoot() {
      const apiRoot = process.env.REACT_APP_API_ROOT
      if (apiRoot) {
          return apiRoot;
      }

        return '/';
    }

    okClick(event) {
      this.setState(
          { 
              isLoading: true
          }
      );

      axios.get(this.getApiRoot() + 'api/rooms?roomMasterName=' + this.state.roomMasterName)
            .then(response => {
                    this.setState(
                        {
                          roomId: response.data.id
                        }
                    );
                    this.addUser();
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

    addUser() {
      this.setState(
        { 
            isLoading: true
        }
      );

      const name = this.state.name;
      axios.post(this.getApiRoot() + 'api/rooms/' + this.state.roomId + '/users', { username: name })
            .then(response => {
                    this.setState(
                        { 
                            isLoading: false,
                            redirect: '/room/' + this.state.roomId + '/user/' + response.data
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
            </div>);
        }
        if (isLoading) {
          return <div>Loading...</div>;
        }
        return (
            <div>
              <h2>Join Room</h2>
              <Form>
                <Form.Control size="lg" type="text" 
                  onChange={this.handleRoomMasterNameChange}
                  value={this.state.roomMasterName}
                  placeholder="Enter game master name room" />
                <br></br>
                <Form.Control size="lg" type="text"
                  onChange={this.handleNameChange}
                  value={this.state.name}
                  placeholder="Enter your name" />
              </Form>
              <br></br>
              <div>
                <Link to="/">
                  <Button variant="secondary" size="lg">
                    Go Back
                  </Button>{' '}
                </Link>
                <Button variant="primary" size="lg" onClick={this.okClick}>
                  Ok !
                </Button>
              </div>
            </div>
          );
    }
}

export default JoinRoom;