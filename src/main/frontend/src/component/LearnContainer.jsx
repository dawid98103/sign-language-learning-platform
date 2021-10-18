import React from 'react';
import styled from 'styled-components';
import LearnWrapper from './LearnWrapper';
import ResponsivePlayer from './ResponsivePlayer';
import MarginContainer from './MarginContainer';
import LearnDescription from '../component/LearnDescription'
import ControlButton from './ControlButton';
import ElementCounter from './ElementCounter';

const ControlButtonContainer = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 15px 0px 15px 0px;
`

function LearnContainer({ videoUrl, description, currentElement, quantityElements, nextElement, prevElement, finishEvent }) {
    return (
        <MarginContainer>
            <LearnWrapper>
                <ElementCounter>
                    {`${currentElement} / ${quantityElements}`}
                </ElementCounter>
                <ResponsivePlayer url={videoUrl} />
                <LearnDescription text={description} />
                <ControlButtonContainer>
                    {currentElement === 1
                        ? <ControlButton text={"< Cofnij"} disabled={true} clickEvent={() => { }} />
                        : <ControlButton text={"< Cofnij"} disabled={false} clickEvent={() => prevElement()} />}

                    {currentElement === quantityElements
                        ? <ControlButton text={"Zakończ"} disabled={false} clickEvent={() => finishEvent()} />
                        : <ControlButton text={"Dalej >"} disabled={false} clickEvent={() => nextElement()} />}
                </ControlButtonContainer>
            </LearnWrapper>
        </MarginContainer>
    )

}

export default LearnContainer