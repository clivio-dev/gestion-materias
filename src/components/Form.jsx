import '../styles/components/Form.css'

const Form = ({ action, onSubmit, children }) => {
  return (
    <form className='form' action={action} onSubmit={onSubmit}>
      { children }
    </form>
  )
}
export default Form