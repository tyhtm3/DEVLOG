import axios from 'axios';

export default axios.create({
    baseURL: 'http://i3a402.p.ssafy.io:8090/devlog/api',
    headers: {
        'Content-type': 'application/json',
    },
})