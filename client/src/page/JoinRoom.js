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
        name: '',
        redirect: null
      };
      this.okClick = this.okClick.bind(this);
      this.handleNameChange = this.handleNameChange.bind(this);
      this.handleRoomIdChange = this.handleRoomIdChange.bind(this);
    }

    componentDidMount() { }

    handleNameChange(event) {
      this.setState({name: event.target.value});
    }

    handleRoomIdChange(event) {
      this.setState({roomId: event.target.value});
    }

    okClick(event) {
      this.setState(
          { 
              isLoading: true
          }
      );

      console.log("this.state.name : " + this.state.name);
      const name =this.state.name;
      axios.post('http://localhost:8080/rooms/' + this.state.roomId + '/users', name)
            .then(response => {
                    this.setState(
                        { 
                            isLoading: false,
                            redirect: '/room/' + this.state.roomId
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
        return (
            <div>
              <h2>Join Room</h2>
              <Form>
                <Form.Control size="lg" type="text" 
                  onChange={this.handleRoomIdChange}
                  value={this.state.roomId}
                  placeholder="Enter ID room" />
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