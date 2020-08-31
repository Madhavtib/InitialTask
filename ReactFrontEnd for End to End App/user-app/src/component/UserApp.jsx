import React, { Component } from 'react';
import ListUserComponent from './ListUsersComponent';
import UserComponent from './UserComponent';
import {BrowserRouter as Router , Route ,Switch} from 'react-router-dom';
class UserApp extends Component {
    render() {
        return (
            <Router>
            <>
              <h1>UserDetail Application</h1>
              <Switch>
                  <Route path="/" exact component={ListUserComponent}/>
                  <Route path="/users" exact component={ListUserComponent}/>
                  <Route path="/users/:id" component={UserComponent}/>
              </Switch>
              </>
              </Router>
        )
    }
}

export default UserApp