import styled from 'styled-components';
import HrefWrapper from './HrefWrapper';
import { Row, Col, Image, ListGroup } from 'react-bootstrap';

const ResponsiveImage = styled(Image)`
    width: 50px;
    height: 50px;
`

const HeaderWithoutPadding = styled.h5`
    padding: 0px;
    margin: 0px;
`
const ColWithoutPadding = styled(Col)`
    padding: 0px;
`

const ActionWrapper = styled.div`
    display: flex;
    align-items: center;
    height: 100%;
    width: 100%;
    cursor: pointer;
    &:hover {
        filter: invert(27%) sepia(40%) saturate(3502%) hue-rotate(332deg) brightness(96%) contrast(91%);
    }
`

const FriendListElement = ({ friend: { username, gainedPoints, avatarUrl }, deleteFriend, changePage }) => {

    return (
        <ListGroup.Item>
            <Row>
                <ColWithoutPadding xs={3}>
                    <ResponsiveImage src={avatarUrl} />
                </ColWithoutPadding>
                <ColWithoutPadding xs={8}>
                    <Row>
                        <HeaderWithoutPadding>
                            <HrefWrapper hrefAction={() => changePage(username)}>
                                {username}
                            </HrefWrapper>
                        </HeaderWithoutPadding>
                    </Row>
                    <Row>
                        {gainedPoints} pkt.
                    </Row>
                </ColWithoutPadding>
                <Col xs={1}>
                    <ActionWrapper onClick={() => deleteFriend(username)}>
                        <Image src={process.env.PUBLIC_URL + "/icons/delfriend.svg"} width={20} />
                    </ActionWrapper>
                </Col>
            </Row >
        </ListGroup.Item >
    )
}

export default FriendListElement;