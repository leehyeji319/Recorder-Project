import axios from 'axios';

const MEMBER_REST_API_URL = "http://localhost:8080/api/members";

class MemberService {

    findAllMembers() {
        return axios.get(MEMBER_REST_API_URL);
    }
}

export default new MemberService()