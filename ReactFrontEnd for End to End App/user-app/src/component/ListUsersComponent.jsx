import React , {Component} from 'react';
import UserDataService from '../service/UserDataService';
class  ListUserComponent extends Component{
    constructor(props)
    {
        super(props)
        this.state ={
            users: [],
            message: null
        }
        this.refreshUsers = this.refreshUsers.bind(this)
        this.deleteUserClicked=this.deleteUserClicked.bind(this)
        this.updateUserClicked=this.updateUserClicked.bind(this)
        this.addUserClicked=this.addUserClicked.bind(this)
    }
    componentDidMount()
    {
        this.refreshUsers();
    }
    refreshUsers()
    {
        UserDataService.retrieveAllUsers()
        .then(
            response =>{
                //console.log(response);
                this.setState({users : response.data})
            }
        )
    }
    deleteUserClicked(id){
        UserDataService.deleteUser(id)
        .then(
            response =>{
                this.setState({message: `Delete of user ${id} Successful`})
                this.refreshUsers()
            }
        )
    }
    updateUserClicked(id)
    {
        //console.log('update' + id)
        this.props.history.push(`/users/${id}`)
    }
    addUserClicked()
    {
        this.props.history.push(`/users/-1`)
    }
    render()
    {
        //console.log('render')
        return(
            <div className="container">
                <h3>All Users</h3>
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                    <thead>
                        <tr>
                            <th>UID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Profession</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                       this.state.users.map(
                           user =>
                              <tr key={user.uid}>
                                  <td>{user.uid}</td>
                                  <td>{user.name}</td>
                                  <td>{user.email}</td>
                                  <td>{user.profession}</td>
                                  <td><button className="btn btn-success" onClick={() => this.updateUserClicked(user.uid)}>Update</button></td>
                                  <td><button className="btn btn-warning" onClick={() => this.deleteUserClicked(user.uid)}>Delete</button></td>
                           </tr>
                        )
                        }
                    </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addUserClicked}>Add</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListUserComponent