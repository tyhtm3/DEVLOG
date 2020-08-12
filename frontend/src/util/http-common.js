import axios from 'axios';
import store from '../store';

export default axios.create({
    baseURL: 'http://localhost:8090/devlog/api',
    headers: {
        'Content-type': 'application/json',
        Authorization : store.state.token,
    },
})