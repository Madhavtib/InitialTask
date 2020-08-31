import React, {Component} from 'react'

import {Formik, Form, Field, ErrorMessage} from 'formik';
import UserDataService from '../service/UserDataService';
class UserComponent extends Component{
    constructor(props)
    {
        super(props)
        this.state ={
            id: this.props.match.params.id,
            name: '',
            email: '',
            profession:''
        }
        this.validate=this.validate.bind(this)
        this.onSubmit=this.onSubmit.bind(this)
    }
    componentDidMount(){
        console.log(this.state.id)
        if(this.state.id == -1){
            return
        }
        UserDataService.retrieveUser(this.state.id)
        .then(
            response => this.setState(
                {
                    name: response.data.name,
                    email: response.data.email,
                    profession: response.data.profession
                }
            )
        )
    }
    onSubmit(values){
        console.log(values);
        let user={
            uid: this.state.id,
            name: values.name,
            email: values.email,
            profession: values.profession

        }
        if(this.state.id == -1){
            UserDataService.addUser(user)
            .then(
                () => this.props.history.push('/users')
            )
        }
        else{
            UserDataService.updateUser(this.state.id,user)
            .then(
                () => this.props.history.push('/users')
            )
        }
       
    }
    validate(values)
    {
        let errors = {}
        if (!values.name){
            errors.name='Enter a name'
        }
        else if(!values.email){
            errors.email='Enter an email'
        }
        else if(!values.profession){
            errors.profession='Enter a profession'
        }
        return errors
    }
    render()
    {
        let {name,email,profession,id}=this.state
        console.log(this.state)
        return(
            <div>
            <h3>User</h3>
            <Formik initialValues={{id,name,email,profession}} 
                    onSubmit={this.onSubmit}
                    validateOnBlur={false}
                    validateOnChange={false}
                    validate={this.validate}
                    enableReinitialize={true}>
                {
                    (props) => (
                        <Form>
                            <ErrorMessage name="name" component="div" className="alert alert-warning"/>
                            <ErrorMessage name="email" component="div" className="alert alert-warning"/>
                            <ErrorMessage name="profession" component="div" className="alert alert-warning"/>
                            <fieldset className='form-group'>
                                <label>Id</label>
                                <Field className="form-control" type="text" name="id" disabled/>
                            </fieldset>
                            <fieldset className='form-group'>
                                <label>Name</label>
                                <Field className="form-control" type="text" name="name" />
                            </fieldset>
                            <fieldset className='form-group'>
                                <label>Email</label>
                                <Field className="form-control" type="text" name="email" />
                            </fieldset>
                            <fieldset className='form-group'>
                                <label>Profession</label>
                                <Field className="form-control" type="text" name="profession" />
                            </fieldset>
                            <button className="btn btn-success" type="submit">Save</button>
                        </Form>
                    )
                }
            </Formik>
        </div>
        )
    }
}
export default UserComponent