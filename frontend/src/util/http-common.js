import axios from 'axios';

export default axios.create({
    baseURL: 'http://localhost:8090/devlog/api',
    headers: {
        'Content-type': 'application/json',
    },
})