import React from 'react';

const Home = (props) => {
    return (
        <div>
            {props.username ? 'Your access token is: ' + props.username : 'You are not logged in'}
        </div>
    );
};

export default Home;