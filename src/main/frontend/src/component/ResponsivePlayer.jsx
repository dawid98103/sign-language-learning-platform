import React from 'react';
import ReactPlayer from 'react-player';
import styled from 'styled-components';

const PlayerWrapper = styled.div`
    position: relative;
    padding-top: 56.25%;
`

const StyledReactPlayer = styled(ReactPlayer)`
    position: absolute;
    top: 0;
    left: 0;
`

const ResponsivePlayer = ({ url }) => {

    return (
        <PlayerWrapper>
            <StyledReactPlayer
                url={url}
                controls={true}
                playing={true}
                width='100%'
                height='100%'
            />
        </PlayerWrapper>
    )
}

export default ResponsivePlayer;