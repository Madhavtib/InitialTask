import axios from 'axios';
class UserDataService{
    retrieveAllUsers()
    {
        return axios.get(`http://localhost:8080/users`);
    }
    deleteUser(id){
        return axios.delete(`http://localhost:8080/users/${id}`);
    }
    retrieveUser(id)
    {
        return axios.get(`http://localhost:8080/users/${id}`);
    }
    updateUser(id,user)
    {
        return axios.put(`http://localhost:8080/users/${id}`,user);
    }
    addUser(user)
    {
        return axios.post(`http://localhost:8080/users`,user);//default header?
    }
}
export default new UserDataService()