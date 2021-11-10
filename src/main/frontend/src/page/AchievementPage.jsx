import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import { Container, ListGroup } from 'react-bootstrap';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import GlobalSpinner from '../component/GlobalSpinner';
import AchievementListElement from '../component/AchievementListElement';


const AchievementPage = () => {
    const [userAchievements, setUserAchievements] = useState([])

    useEffect(async () => {
        const calculateAchievements = async () => {
            await AxiosClient.post(`/achievement`)
        }
        const fetchAchievements = async () => {
            const { data } = await AxiosClient.get(`/achievement`)
            setUserAchievements(data)
        }

        await calculateAchievements()
        await fetchAchievements()
    }, [])

    return (
        <MarginContainer>
            <GlobalContentWrapper>
                {userAchievements.length === 0 ?
                    <GlobalSpinner />
                    :
                    <Container>
                        <ListGroup variant="flush">
                            {
                                userAchievements.map(userAchievement => {
                                    return (
                                        <AchievementListElement achievement={userAchievement} />
                                    )
                                })
                            }
                        </ListGroup>
                    </Container>
                }
            </GlobalContentWrapper>
        </MarginContainer >
    )
}

export default AchievementPage;