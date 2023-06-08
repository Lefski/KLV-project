import {
    useLocation,
    useNavigate,
    useParams,
} from 'react-router-dom';

/**
 * Enhances a React component with router props from react-router-dom.
 *
 * @param {function(*): *} Component - The component to be enhanced.
 * @return {function(Object): *} - The enhanced component with router props.
 */
export function withRouter(Component) {
    /**
     * A higher-order component that passes
     * router props to the wrapped component.
     *
     * @param {Object} props - The props passed to the component.
     * @return {React.Element} - The rendered component with router props.
     */
    function ComponentWithRouterProp(props) {
        const location = useLocation();
        const navigate = useNavigate();
        const params = useParams();
        return (
            <Component
                {...props}
                router={{location, navigate, params}}
            />
        );
    }

    return ComponentWithRouterProp;
}
