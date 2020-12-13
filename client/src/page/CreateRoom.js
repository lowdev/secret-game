import React from 'react';
import { Button, Form } from 'react-bootstrap';
import axios from 'axios';
import { Redirect, Link } from "react-router-dom";

class CreateRoom extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        error: null,
        isLoading: false,
        roomId: null,
        name: '',
        redirect: null
      };
      this.okClick = this.okClick.bind(this);
      this.handleChange = this.handleChange.bind(this);
    }
  
    componentDidMount() { }

    handleChange(event) {
      this.setState({name: event.target.value});
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


      console.log("toto : " + this.state.name);
      axios.post(this.getApiRoot() + 'api/rooms', { username: this.state.name })
            .then(response => {
                    this.setState(
                        { 
                            isLoading: false,
                            roomId: response.data.id,
                            redirect: '/room/' + response.data.id + '/user/' +  response.data.gameMasterId
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

    BackButton() {
      return (
        <Link to="/">
          <Button variant="secondary" size="lg">
            Go Back
          </Button>{' '}
        </Link>
      );
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
            <h2>Create Room</h2>
            <Form>
                <Form.Control size="lg" type="text"
                  onChange={this.handleChange}
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

  export default CreateRoom;