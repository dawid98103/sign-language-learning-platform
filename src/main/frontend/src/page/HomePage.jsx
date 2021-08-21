import React from 'react'
import CenteredMarginContainer from '../component/CenteredMarginContainer';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { Col, Row, Image, Button } from 'react-bootstrap';

const CenteredMarginContainerWithBackground = styled(CenteredMarginContainer)`
    background-image: 
    background-repeat: no-repeat;
    background-size: cover;
`

const RightColWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    height: 100%;
`

const HomeTextBlock = styled.span`
    font-size: 32px;
    color: white;
    font-weight: 700;
    line-height: 40px;
`

const ButtonWrapper = styled.div`
    display: flex;
    width: 70%;
    margin-top: 40px;
    padding: 15px;
    justify-content: space-around;
`

const TransparentButton = styled(Button)`
    background-color: transparent;
`

function HomePage(params) {
    return (
        <CenteredMarginContainerWithBackground withBackground={true}>
            <div>
                <Row>
                    <Col xs={1}></Col>
                    <Col>
                        <Image src={process.env.PUBLIC_URL + "/icons/hand-sign.svg"} width="340" height="340" />
                    </Col>
                    <Col>
                        <RightColWrapper>
                            <div>
                                <HomeTextBlock>
                                    Darmowy, ciekawy i skuteczny sposób na naukę języka migowego!
                                </HomeTextBlock>
                            </div>
                            <ButtonWrapper>
                                <Link to='/learn'>
                                    <TransparentButton variant="success" size="lg">
                                        Rozpocznij naukę!
                                    </TransparentButton>
                                </Link>
                                <Link to='/register'>
                                    <TransparentButton variant="primary" size="lg">
                                        Zarejestruj się
                                    </TransparentButton>
                                </Link>
                            </ButtonWrapper>
                        </RightColWrapper>
                    </Col>
                    <Col xs={1}></Col>
                </Row>
            </div>
        </CenteredMarginContainerWithBackground>
    )
}

export default HomePage