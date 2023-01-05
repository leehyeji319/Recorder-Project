import React from 'react';
import MemberService from '../../services/MemberService';

class MemberComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            members:[]
        }
    }

    componentDidMount(){
        MemberService.findAllMembers().then((response) => {
            this.setState({ members: response.data})
        });
    }

    render () {
        return (
            <div>
                <h1 className='text-center'> Member List</h1>
                <table className='table table-striped'>
                    <thead>
                        <tr>

                            <td> Member Id</td>
                            <td> Member Name</td>
                            <td> Member Email</td>
                        </tr>
                        
                    </thead>
                    <tbody>
                        {
                            this.state.members.map(
                                member =>
                                <tr key = {member.id}>
                                    <td> {member.id}</td>
                                    <td> {member.username}</td>
                                    <td> {member.email}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default MemberComponent