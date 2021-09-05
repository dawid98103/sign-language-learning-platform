import React, { useState, useEffect, useContext } from 'react';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import LearnContainer from '../component/LearnContainer';
import GlobalSpinner from '../component/GlobalSpinner';
import { GlobalContext } from '../context/GlobalContext';
import history from '../config/history';

function LearnPage({ match }) {

    const [currentElement, setCurrentElement] = useState(0)
    const { state, dispatch } = useContext(GlobalContext);
    const [elements, setElements] = useState([]);

    useEffect(() => {
        fetchElements();
    }, []);

    const fetchElements = async () => {
        const { data } = await AxiosClient.get(`/lessons/${match.params.lessonId}/stage/${match.params.stageId}/element`)
        setElements(data);
    }

    const nextElement = () => {
        if (elements.length > currentElement + 1) {
            setCurrentElement(currentElement + 1)
        }
    }

    const prevElement = () => {
        if (currentElement - 1 >= 0) {
            setCurrentElement(currentElement - 1)
        }
    }

    const finishLesson = async () => {
        const response = await AxiosClient.post(`/lessons/${match.params.lessonId}/stage/${match.params.stageId}/finish`)
        console.log(response);
        history.push("/lesson")
    }

    return (
        <MarginContainer>
            {elements.length > 0
                ? <LearnContainer videoUrl={elements[currentElement].videoUrl} description={elements[currentElement].description} currentElement={currentElement + 1} quantityElements={elements.length} nextElement={() => nextElement()} prevElement={() => prevElement()} finishEvent={() => finishLesson()} />
                : <GlobalSpinner />}
        </MarginContainer>
    )
}

export default LearnPage

