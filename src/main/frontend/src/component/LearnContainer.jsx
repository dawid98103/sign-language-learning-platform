import React from 'react';
import ReactPlayer from 'react-player';
import MarginContainer from './MarginContainer';
import styled from 'styled-components';
import LearnDescription from '../component/LearnDescription'
import { Button } from 'react-bootstrap';

const LearnWrapper = styled.div`
    margin: auto;
    padding: 30px;
    border: 2px solid #e5e5e5;
    border-radius: 25px;
`
const ElementLeftCounter = styled.div`
    margin: 5px 0px 15px 0px;
    text-align: center;
    font-weight: bold;
    font-size: 1.5rem;
    padding: 5px;
    border-radius: 15px;
`

const ControlButtonContainer = styled.div`
    display: flex;
    justify-items: center;
    padding: 10px;
`

function LearnContainer({ videoUrl, description, currentElement, quantityElements, nextElement, prevElement }) {

    return (
        <MarginContainer>
            <LearnWrapper>
                <ElementLeftCounter>
                    {`${currentElement} / ${quantityElements}`}
                </ElementLeftCounter>
                <ReactPlayer url={videoUrl} />
                <LearnDescription text={description} />
                <ControlButtonContainer>
                    <Button variant="outline-light" size="lg" active onClick={() => prevElement()}>Cofnij</Button>
                    <Button variant="outline-light" size="lg" active onClick={() => nextElement()}>Dalej</Button>
                </ControlButtonContainer>
            </LearnWrapper>
        </MarginContainer>
    )

}

export default LearnContainer